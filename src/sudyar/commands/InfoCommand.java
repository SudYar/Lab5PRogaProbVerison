package sudyar.commands;

import sudyar.data.StudyGroupCollection;

/**
 * Выводит общую информацию об коллекции
 */

public class InfoCommand  extends AbstractCommand{
    private StudyGroupCollection studyGroupCollection;

    public InfoCommand(StudyGroupCollection studyGroupCollection) {
        super("info", "Выводит тип и количество элементов коллеции");
        this.studyGroupCollection = studyGroupCollection;
    }

    /**
     * @param argument - не используется, может быть null
     * @return false
     */
    @Override
    public boolean execute(String argument) {
        System.out.println(studyGroupCollection.getInfo());
        return false;
    }

    public InfoCommand(String name, String description) {
        super(name, description);
    }
}
