package sudyar.commands;

import java.util.Objects;

/**
 * Класс AbstractCommand содержит основные методы, имя и описание команды
 */

public abstract class AbstractCommand implements Command {

    private String name;
    private String descriptionArgument;
    private String description;

    public AbstractCommand(String name, String description){
        this.description = description;
        this.name = name;
    }
    public AbstractCommand(String name,String descriptionArgument, String description){
        this.description = description;
        this.descriptionArgument = descriptionArgument;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getDescriptionArgument() {
        return descriptionArgument;
    }

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
