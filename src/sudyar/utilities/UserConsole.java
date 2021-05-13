package sudyar.utilities;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import sudyar.commands.Commands;
import sudyar.data.StudyGroupCollection;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class UserConsole {
    private final StudyGroupCollection studyGroupCollection;
    private HashSet<String> pathSet = new HashSet<>();
    private static BufferedReader scanner /*= new BufferedReader(new InputStreamReader(System.in))*/;
    private static boolean script = false;

    public UserConsole(StudyGroupCollection studyGroupCollection) {
        this.studyGroupCollection = studyGroupCollection;
    }

    public static String readLine () {
        BufferedReader bufferedReader = scanner;
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                if (script) {
                    System.out.println("В скрипте пустая строка, вводите сами");
                    script = false;
                } else {
                System.out.println("Завершение программы (без сохранения)");
                System.exit(0);
                }
                return "";
            }
            else return line;
        } catch (IOException e) {
            return "";
        }
    }

    public void commandMode (Commands commands) {
        String line;
        script = false;
        scanner = new BufferedReader( new InputStreamReader(System.in));
        while (true){
            System.out.print("$");
            line = readLine();
            String command[] = line.trim().split(" ");
            String argument;
            if (command.length>1) argument = command[1];
            else argument = null;
            for (int i = 0; i < command.length; i++) command[i] = command[i].trim();
            if (commands.getCommands().containsKey(command[0])) {
                if ("help".equals(command[0])) {
                    System.out.println(commands);
                }
                else try {
                    if ("execute_script".equals(command[0])) {
                        scriptMode(argument, commands, "");
                    }
                    else commands.getCommands().get(command[0]).execute(argument);
                } catch (IllegalArgumentException illegalArgumentException) {
                    System.out.println(illegalArgumentException.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("Error: Нет такой команды");

        }

    }

    public static void changeScanner(BufferedReader scan){
        UserConsole.scanner = scan;
    }


    public static void changeScript(boolean b){
        UserConsole.script = b;
    }


    public void scriptMode (String path, Commands commands, String lastPath){
        if (path == null) throw new IllegalArgumentException("Нет аргументов");
        if (!pathSet.contains(path)) {
            pathSet.add(path);
            try {
                File file = new File(path);
                if (!file.exists()) throw new FileNotFoundException("Файл не найден");
                else if (!file.canRead()) throw new IOException("Не могу прочесть с файла");
                else {
                    BufferedReader scanner = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
                    UserConsole.changeScanner(scanner);
                    changeScript(true);
                    String line;
                    while (script) {
                        line = readLine();
                        if (!script) break;
                        String[] command = line.split(" ");
                        String argument;
                        if (command.length > 1) argument = command[1];
                        else argument = null;
                        for (int i = 0; i < command.length; i++) command[i] = command[i].trim();
                        if (commands.getCommands().containsKey(command[0])) {
                            if ("help".equals(command[0])) {
                                System.out.println(commands);
                            } else try {
                                if ("execute_script".equals(command[0])) scriptMode(argument, commands, path);
                                else commands.getCommands().get(command[0]).execute(argument);
                            } catch (IllegalArgumentException illegalArgumentException) {
                                System.out.println(illegalArgumentException.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else System.out.println("Error: Нет такой команды");
                    }
                }

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            try {
                scanner.close();
                if("".equals(lastPath)) scanner = new BufferedReader( new InputStreamReader(System.in));
                else scanner = new BufferedReader(new InputStreamReader(new FileInputStream(lastPath)));
                pathSet.remove(path);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
