package sudyar.commands;

/**
 *
 * Интерфейс Command
 *
 */

public interface Command {
    String getDescription();
    String getName();
    String getDescriptionArgument();
    boolean execute(String argument);
}
