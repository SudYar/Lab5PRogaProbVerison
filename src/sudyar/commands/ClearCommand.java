package sudyar.commands;

import sudyar.data.StudyGroupCollection;

/**
 * Очищает коллекцию
 *
 *
 */

public class ClearCommand  extends AbstractCommand{
    private StudyGroupCollection studyGroupCollection;

    public ClearCommand(StudyGroupCollection studyGroupCollection) {
        super("clear", "Очистить коллекцию");
        this.studyGroupCollection = studyGroupCollection;
    }

    @Override
    public String toString() {
        return this.getName()+" : " + this.getDescription();
    }

    /**
     *Выполнение команды
     * @return Статус заверщения команды
     */
    @Override
    public boolean execute(String argument) {
        studyGroupCollection.clear();
        System.out.println("Коллекция очищена");
        return false;
    }
}
