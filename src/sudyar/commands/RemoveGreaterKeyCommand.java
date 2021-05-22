package sudyar.commands;

import sudyar.data.StudyGroupCollection;

/**
 * Удаляет из коллекции все элементы, ключ которых превышает заданный
 */
public class RemoveGreaterKeyCommand extends AbstractCommand{

    private StudyGroupCollection studyGroupCollection;

    public RemoveGreaterKeyCommand(StudyGroupCollection studyGroupCollection) {
        super("remove_greater_key", "id", "Удалить из коллекции все аргументы, ключ которых превышает заданный");
        this.studyGroupCollection = studyGroupCollection;
    }

    /**
     * Проходит по множеству ключей и удаляет все элементы, чей ключ превышает заданный
     * @param argument - int > 0, в виде строки. id, с которым будет сравнивать
     * @return false
     * @throws IllegalArgumentException
     */
    @Override
    public boolean execute(String argument) {
        if (argument == null) throw new IllegalArgumentException("Нет аргументов, требуется id типа int");
        try {
            Integer id = Integer.parseInt(argument);
            for (Integer i : studyGroupCollection.getCollection().keySet()) {
                if (id.compareTo(i) < 0) {
                    studyGroupCollection.remove(i);
                    System.out.println("Элемент с Id " + i + " удален из коллекции");
                }
            }
            System.out.println("Удаление завершено");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный тип аргумента. В аргументы подается id типа int");
        }
        return false;
    }
}
