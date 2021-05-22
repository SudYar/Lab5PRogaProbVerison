package sudyar.commands;

/**
 * Выходит из программы без сохранения
 */

public class ExitCommand  extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Завершение программы (без сохранения в файл)");
    }

    /**
     * Просто убивает программу
     * @param argument - можно null, не используется
     * @return false
     */
    @Override
    public boolean execute(String argument) {
        System.out.println("Завершение программы (без сохранения в файл)");
        System.exit(0);
        return false;
    }

}
