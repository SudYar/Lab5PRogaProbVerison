package sudyar.data;

import sudyar.exception.DuplicateException;
import sudyar.utilities.StudyGroupParser;

import java.util.*;

/**
 * Отвечает за коллекцию, а также управление ей
 */
public class StudyGroupCollection {
    private HashMap<Integer, StudyGroup > collection = new HashMap<>();
    private HashSet<String> passportIdSet = new HashSet<>();


    public StudyGroupCollection() {
    }

    /**
     * Добавляет новую группу, с уже готовым id
     * @param s
     * @throws DuplicateException
     */
    public void add(StudyGroup s) throws DuplicateException {
        if (collection.containsKey(s.getId())) throw new DuplicateException("Повторение id");
        if ((s.getGroupAdmin() != null) && (passportIdSet.contains(s.getGroupAdmin().getPassportID())))
            throw new DuplicateException("ERROR: Повторение passportId админа");
        collection.put(s.getId(), s);
        if (s.getGroupAdmin() != null) passportIdSet.add(s.getGroupAdmin().getPassportID());
    }

    /**
     * Принимая поля группы, создает новую и добавляет в коллекцию
     * @param id
     * @param name
     * @param x
     * @param y
     * @param studentCount
     * @param formOfEducation
     * @param semester
     * @param groupAdmin
     * @throws DuplicateException
     */
    public void update(int id, String name, double x, float y, int studentCount,
                       FormOfEducation formOfEducation, Semester semester, Person groupAdmin) throws DuplicateException{

        Coordinates coordinates = StudyGroupParser.parseCoordinates(x,y);
        StudyGroup s = new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        if (collection.get(id).getGroupAdmin() != null) passportIdSet.remove(collection.get(id).getGroupAdmin().getPassportID());
        collection.put(s.getId(), s);
        if (s.getGroupAdmin() != null) passportIdSet.add(s.getGroupAdmin().getPassportID());
    }

    /**
     * Генерирует новую группу, самостоятельно генерируя id и дату
     * @param name
     * @param x
     * @param y
     * @param studentCount
     * @param formOfEducation
     * @param semester
     * @param groupAdmin
     * @throws DuplicateException
     */
    public void insert(String name, double x, float y, int studentCount,
                    FormOfEducation formOfEducation, Semester semester, Person groupAdmin) throws DuplicateException{
        int id;
        if (collection.isEmpty()) id = 1;
        else id = Collections.max(collection.keySet()) + 1;
        Coordinates coordinates = StudyGroupParser.parseCoordinates(x,y);
        StudyGroup s = new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        add(s);
    }

    /**
     * Удаление по id
     * @param id
     */
    public void remove(int id){
        if (collection.containsKey(id)) {
            if (collection.get(id).getGroupAdmin() != null) passportIdSet.remove(collection.get(id).getGroupAdmin().getPassportID());
            collection.remove(id);
        }
    }

    public void clear(){
        collection.clear();
        passportIdSet.clear();
    }


    public HashMap<Integer, StudyGroup> getCollection() {
        return collection;
    }

    public StudyGroup getById(int id){
        return collection.get(id);
    }

    public String getInfo(){
        return "HashMap коллекция, размер: " + collection.size();
    }

    public boolean containsPassportId(String passportId) {
        return passportIdSet.contains(passportId);
    }

    public boolean isEmpty(){
        return collection.isEmpty();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i: collection.keySet()) {
            result += collection.get(i) + "\n";
        }
        if ("".equals(result)) return "Коллекция пуста";
        else return result.trim();
    }
}
