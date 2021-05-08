package sudyar.commands;

import sudyar.data.StudyGroupCollection;
import sudyar.utilities.FileParser;

public class SaveCommand extends AbstractCommand {
    private final StudyGroupCollection studyGroupCollection;
    private final FileParser fileParser;
    public SaveCommand(StudyGroupCollection studyGroupCollection, FileParser fileParser) {
        super("save", "Сохранение коллекции в файл");
        this.studyGroupCollection = studyGroupCollection;
        this.fileParser = fileParser;

    }

    @Override
    public boolean execute(String argument) {
        if (fileParser.canWrite) fileParser.unParse(studyGroupCollection);
        else System.out.println("Нет возможности сохранить в этот файл");
        return false;
    }
}
