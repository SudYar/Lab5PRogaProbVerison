package sudyar.commands;

import sudyar.data.FormOfEducation;
import sudyar.data.StudyGroupCollection;
import sudyar.utilities.StudyGroupParser;

public class RemoveAllByFormOfEducationCommand  extends AbstractCommand{
    private StudyGroupCollection studyGroupCollection;

    public RemoveAllByFormOfEducationCommand(StudyGroupCollection studyGroupCollection) {
        super("remove_all_by_form_of_education", "formOfEducation", "удалить все элементы с такой формой обучения");
        this.studyGroupCollection = studyGroupCollection;
    }

    @Override
    public boolean execute(String argument) {
        if (argument == null) throw new IllegalArgumentException("Нет аргументов, возможные варианты: " + FormOfEducation.nameList());
        FormOfEducation formOfEducation = StudyGroupParser.parseFormOfEducation(argument);
        if (formOfEducation == null)throw new IllegalArgumentException("Не найдена такая форма образования. Возможные варианты: " + FormOfEducation.nameList());
        else {
            for (Integer i : studyGroupCollection.getCollection().keySet()) {
                if (formOfEducation.equals(studyGroupCollection.getById(i).getFormOfEducation())) {
                    studyGroupCollection.remove(i);
                    System.out.println("Элемент с Id " + i + " удален из коллекции");
                }
            }
        }
        System.out.println("Удаление завершено");
        return false;
    }
}
