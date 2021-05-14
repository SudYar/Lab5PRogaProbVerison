package sudyar;

import sudyar.data.*;
import sudyar.utilities.FileParser;
import sudyar.utilities.StudyGroupParser;
import sudyar.commands.*;
import sudyar.utilities.UserConsole;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        FileParser fileParser;

        try {
            if (args != null && args.length>0) {
                fileParser = new FileParser(args[0]);
                if (!fileParser.canRead) {
                    System.out.println("Нет прав на чтение файла, работаем с чистого листа");
                }
                if (!fileParser.canWrite) {
                    System.out.println("Нет прав на запись в файл, нельзя будет сохранить коллекцию");
                }
            }else throw new  FileNotFoundException();
        } catch (FileNotFoundException e) {
            fileParser = new FileParser();
            System.out.println("Файла нет, работаем локально");
        }

        StudyGroupCollection newCollection;

        if (fileParser.canRead) {
            newCollection = fileParser.parse();
        }
        else newCollection = new StudyGroupCollection();

        HashSet<Command> set = new HashSet<>();
        set.add(new HelpCommand());
        set.add(new InfoCommand(newCollection));
        set.add(new ShowCommand(newCollection));
        set.add(new InsertCommand(newCollection));
        set.add(new UpdateCommand(newCollection));
        set.add(new RemoveKeyCommand(newCollection));
        set.add(new ClearCommand(newCollection));
        set.add(new RemoveGreaterCommand(newCollection));
        set.add(new ReplaceIfLowe(newCollection));
        set.add(new RemoveGreaterKeyCommand(newCollection));
        set.add(new RemoveAllByFormOfEducationCommand(newCollection));
        set.add(new SumOfStudentsCountCommand(newCollection));
        set.add(new CountByStudentsCountCommand(newCollection));
        set.add(new ExecuteScriptCommand());
        set.add(new SaveCommand(newCollection, fileParser));
        set.add(new ExitCommand());

        Commands commands = new Commands(set);
        UserConsole userConsole = new UserConsole();
        userConsole.commandMode(commands);
    }
}
