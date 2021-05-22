package sudyar.commands;

import sudyar.data.StudyGroupCollection;
import sudyar.utilities.StudyGroupParser;

/**
 * Считает количество элементов коллекции с таким же studentsCount
 */

public class CountByStudentsCountCommand extends AbstractCommand{
    private StudyGroupCollection studyGroupCollection;

    public CountByStudentsCountCommand(StudyGroupCollection studyGroupCollection) {
        super("count_by_students_count", "studentsCount", "Вывести колличество элементов, значение studentsCount которых равно данному");
        this.studyGroupCollection = studyGroupCollection;
    }

    /**
     *
     * @param argument - studentCount, в виде строки
     * @return false
     * @throws IllegalArgumentException
     */

    @Override
    public boolean execute(String argument) {
        if (argument == null) throw new IllegalArgumentException("Нет аргументов, требуется id типа int");
        Integer studentsCount = StudyGroupParser.parseStudentsCount(argument);
        if (studentsCount == null) throw new IllegalArgumentException("Неверный аргумент, пожалуйста, введите int > 0");
        else {
            int count = 0;
            for (Integer i : studyGroupCollection.getCollection().keySet()) {
                if (studentsCount.equals(studyGroupCollection.getById(i).getStudentsCount()))
                    count++;
            }
            System.out.println("Колличество элементов с таким же studentCount: " + count);
        }

        return false;
    }
}
