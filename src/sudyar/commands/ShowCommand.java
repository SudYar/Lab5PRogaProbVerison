package sudyar.commands;

import sudyar.data.StudyGroupCollection;

/**
 * Выводит все элементы коллекции
 */
public class ShowCommand extends AbstractCommand {
    private StudyGroupCollection studyGroupCollection;

    public ShowCommand(StudyGroupCollection studyGroupCollection) {
        super("show", "Вывести все элементы коллекции");
        this.studyGroupCollection = studyGroupCollection;
    }

    public ShowCommand(String name, String description) {
        super(name, description);
    }

    /**
     * Просто использование toString класса, отвечающего за коллекцию
     * @param argument - не используется, может быть Null
     * @return false
     */
    @Override
    public boolean execute(String argument) {
        System.out.println(studyGroupCollection);
        return false;
    }
}
