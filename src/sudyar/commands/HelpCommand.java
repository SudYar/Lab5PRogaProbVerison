package sudyar.commands;

public class HelpCommand extends AbstractCommand{
    @Override
    public boolean execute(String argument) {
        return false;
    }

    public HelpCommand(String name, String description) {
        super(name, description);
    }

    public HelpCommand(){
        super("help", "Вывод описания команд");
    }
}
