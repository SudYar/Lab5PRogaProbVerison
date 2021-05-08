package sudyar.commands;

import sudyar.data.StudyGroupCollection;

public class ShowCommand extends AbstractCommand {
    private StudyGroupCollection studyGroupCollection;

    public ShowCommand(StudyGroupCollection studyGroupCollection) {
        super("show", "Вывести все элементы коллекции");
        this.studyGroupCollection = studyGroupCollection;
    }

    public ShowCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute(String argument) {
        System.out.println(studyGroupCollection);
        return false;
    }
}
