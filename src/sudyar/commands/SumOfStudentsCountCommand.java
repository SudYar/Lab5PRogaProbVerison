package sudyar.commands;

import sudyar.data.StudyGroupCollection;

/**
 * Считает сумму studentsCount всех элементов
 */
public class SumOfStudentsCountCommand extends AbstractCommand {
    private StudyGroupCollection studyGroupCollection;

    public SumOfStudentsCountCommand (StudyGroupCollection studyGroupCollection){
        super("sum_of_student_count" , "Выводит сумму studentsCount из всех элементов коллекции");
        this.studyGroupCollection = studyGroupCollection;
    }

    /**
     * Проходит по всем элементам коллекции и просто складывает studentsCount каждого
     * @param argument - не используется, может быть Null
     * @return false
     */
    @Override
    public boolean execute(String argument) {
        int sumOfStudentsCount = 0;
        for (Integer i : studyGroupCollection.getCollection().keySet()) {
                sumOfStudentsCount+= studyGroupCollection.getById(i).getStudentsCount();
        }
        if (sumOfStudentsCount == 0) System.out.println("Коллекция пуста, тут нет групп");
        else System.out.println("Колличество студентов: " + sumOfStudentsCount);
        return false;
    }
}
