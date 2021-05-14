package sudyar.utilities;

import sudyar.commands.Commands;

import java.io.*;
import java.util.ArrayDeque;

public class UserConsole {
    private static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    private static boolean script = false;
    private static ArrayDeque<String> arrayString = new ArrayDeque<>();
    private static ArrayDeque<String> pathStack = new ArrayDeque<>();

    public UserConsole() {
    }

    public static String readLine () {
        String line;
        if (!script) {
            BufferedReader bufferedReader = scanner;
            try {
                line = bufferedReader.readLine();
                if (line == null) {
                    System.out.println("Завершение программы (без сохранения)");
                    System.exit(0);
                    return "";
                } else return line;
            } catch (IOException e) {
                return "Непредвиденная ошибка: " + e.getMessage();
            }
        }
        else {
            if (arrayString.isEmpty()) UserConsole.changeScript(false);
            while (script) {
                line = arrayString.pollFirst();
                if (line == null) {
                    System.out.println("Непредвиденная ошибка, команды закончились");
                    UserConsole.changeScript(false);
                } else {
                    if ("".equals(line)) {
                        System.out.println("Скрипт " + pathStack.pollLast() + " закончился");
                        if (pathStack.isEmpty()) UserConsole.changeScript(false);
                    } else {
                        System.out.println(line);
                        return line;
                    }
                }
            }
            System.out.println(" <Перешли в ручной режим>");
            return readLine();
        }
    }

    public void commandMode (Commands commands) {
        String line;
        script = false;
        scanner = new BufferedReader( new InputStreamReader(System.in));
        while (true){
            System.out.print("$");
            line = readLine();
            String[] command = line.trim().split(" ");
            String argument;
            if (command.length>1) argument = command[1];
            else argument = null;
            for (int i = 0; i < command.length; i++) command[i] = command[i].trim();
            if (commands.getCommands().containsKey(command[0])) {
                if ("help".equals(command[0])) {
                    System.out.println(commands);
                }
                else try {
                    if ("execute_script".equals(command[0])) scriptMode(argument, commands);
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


    public static void changeScript(boolean b){
        script = b;
    }


    public void scriptMode (String path, Commands commands){
        if (path == null) throw new IllegalArgumentException("Нет аргументов");
        if (!pathStack.contains(path)) {
            File file = new File(path);
            if (file.exists() && file.canRead()) {
                try {
                    BufferedReader scanner = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    ArrayDeque<String> text = new ArrayDeque<>();
                    String line = scanner.readLine();
                    while (line != null) {
                        if ("".equals(line)) text.add(" ");
                        else text.add(line);
                        line = scanner.readLine();
                    }
                    arrayString.addFirst(""); /* Обозначает конец скрипта */
                    while (!text.isEmpty()) arrayString.addFirst(text.pollLast());
                    pathStack.addLast(path);
                    changeScript(true);
                } catch (IOException fileNotFoundException) {
                    System.out.println("Скрипт не найден");
                }
                String line;
                while (script) {
                    System.out.print("$");
                    line = readLine();
                    if (!script) break;
                    String[] command = line.trim().split(" ");
                    String argument;
                    if (command.length > 1) argument = command[1];
                    else argument = null;
                    for (int i = 0; i < command.length; i++) command[i] = command[i].trim();
                    if (commands.getCommands().containsKey(command[0])) {
                        if ("help".equals(command[0])) {
                            System.out.println(commands);
                        } else try {
                            if ("execute_script".equals(command[0])) scriptMode(argument, commands);
                            else commands.getCommands().get(command[0]).execute(argument);
                        } catch (IllegalArgumentException illegalArgumentException) {
                            System.out.println(illegalArgumentException.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else System.out.println("Не могу прочесть этот файл (возможно его не существует)");
        }
        else System.out.println("Скрипт уже работает");
    }
}
