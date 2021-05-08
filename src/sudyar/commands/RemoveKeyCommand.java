package sudyar.commands;

import sudyar.data.StudyGroupCollection;

public class RemoveKeyCommand extends AbstractCommand{
    private StudyGroupCollection studyGroupCollection;

    public RemoveKeyCommand(StudyGroupCollection studyGroupCollection) {
        super("remove", "id", "Удалить элемент с данным id из коллекции");
        this.studyGroupCollection = studyGroupCollection;
    }

    @Override
    public boolean execute(String argument) throws IllegalArgumentException {
        if (argument == null) throw new IllegalArgumentException("Нет аргументов, требуется id типа int");
        try {
            int id = Integer.parseInt(argument);
            if (!studyGroupCollection.getCollection().containsKey(id))
                throw new IllegalArgumentException("Такого Id нет в коллекции");
            studyGroupCollection.remove(id);
            System.out.println("Элемент удален из коллекции");
            return false;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный тип аргумента. В аргументы подается id типа int");
        }
    }
}
