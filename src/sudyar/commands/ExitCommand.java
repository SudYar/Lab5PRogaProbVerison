package sudyar.commands;

public class ExitCommand  extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Завершение программы (без сохранения в файл)");
    }

    @Override
    public boolean execute(String argument) {
        System.out.println("Завершение программы (без сохранения в файл)");
        System.exit(0);
        return false;
    }

}
