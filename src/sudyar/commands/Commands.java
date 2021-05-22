package sudyar.commands;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Класс, в котором хранятся все команды
 */
public class Commands {
    HashMap<String, Command> commands = new HashMap<>();

    public Commands(ClearCommand clearCommand,
                    CountByStudentsCountCommand countByStudentsCountCommand,
                    ExecuteScriptCommand executeScriptCommand,
                    ExitCommand exitCommand,
                    HelpCommand helpCommand,
                    InfoCommand infoCommand,
                    RemoveAllByFormOfEducationCommand removeAllByFormOfEducationCommand,
                    RemoveGreaterCommand removeGreaterCommand,
                    RemoveKeyCommand removeKeyCommand,
                    ReplaceIfLowe replaceIfLowe,
                    SaveCommand saveCommand,
                    ShowCommand showCommand,
                    SumOfStudentsCountCommand sumOfStudentsCountCommand,
                    UpdateCommand updateCommand) {
        commands.put(clearCommand.getName(), clearCommand);
        commands.put(countByStudentsCountCommand.getName(), countByStudentsCountCommand);
        commands.put(executeScriptCommand.getName(), executeScriptCommand);
        commands.put(exitCommand.getName(), exitCommand);
        commands.put(helpCommand.getName(), helpCommand);
        commands.put(infoCommand.getName(), infoCommand);
        commands.put(removeAllByFormOfEducationCommand.getName(), removeAllByFormOfEducationCommand);
        commands.put(removeGreaterCommand.getName(), removeGreaterCommand);
        commands.put(removeKeyCommand.getName(), removeKeyCommand);
        commands.put(replaceIfLowe.getName(), replaceIfLowe);
        commands.put(saveCommand.getName(), saveCommand);
        commands.put(showCommand.getName(), showCommand);
        commands.put(sumOfStudentsCountCommand.getName(), sumOfStudentsCountCommand);
        commands.put(updateCommand.getName(), updateCommand);
    }

    /**
     * Удобный конструктор, чтобы быстрее добавлять кучу объектов команд
     * @param set
     */
    public Commands(HashSet<Command> set){
        System.out.println("Введите help, чтобы вывести справку по доступным командам");
        for (Command command: set) {
            commands.put(command.getName(), command);
        }
    }


    public HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     *
     * @return String с названиями, описанием аргументов и самих команд
     */
    @Override
    public String toString() {
        String result = "";
        for (String i: commands.keySet()) {
            result+= i + (commands.get(i).getDescriptionArgument() == null ? "" : " "+ commands.get(i).getDescriptionArgument() ) + "\t\t" + commands.get(i).getDescription() + "\n";
        }

        return result.trim();
    }
}
