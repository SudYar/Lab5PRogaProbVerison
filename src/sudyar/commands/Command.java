package sudyar.commands;

/**
 *
 * Интерфейс Command
 * имеет поля имя, описание и аргумент и метод execute
 */

public interface Command {
    String getDescription();
    String getName();
    String getDescriptionArgument();
    boolean execute(String argument);
}
