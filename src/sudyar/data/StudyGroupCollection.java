package sudyar.data;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import sudyar.exception.DuplicateException;
import sudyar.utilities.FileParser;
import sudyar.utilities.StudyGroupParser;

import java.io.IOException;
import java.util.*;

public class StudyGroupCollection {
    private HashMap<Integer, StudyGroup > collection = new HashMap<>();
    private HashSet<String> passportIdSet = new HashSet<>();


    public StudyGroupCollection() {
    }

    public void add(StudyGroup s) throws DuplicateException {
        if (collection.containsKey(s.getId())) throw new DuplicateException("Повторение id");
        if ((s.getGroupAdmin() != null) && (passportIdSet.contains(s.getGroupAdmin().getPassportID())))
            throw new DuplicateException("ERROR: Повторение passportId админа");
        collection.put(s.getId(), s);
        if (s.getGroupAdmin() != null) passportIdSet.add(s.getGroupAdmin().getPassportID());
    }
    public void update(int id, String name, double x, float y, int studentCount,
                       FormOfEducation formOfEducation, Semester semester, Person groupAdmin) throws DuplicateException{

        Coordinates coordinates = StudyGroupParser.parseCoordinates(x,y);
        StudyGroup s = new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        if (collection.get(id).getGroupAdmin() != null) passportIdSet.remove(collection.get(id).getGroupAdmin().getPassportID());
        collection.put(s.getId(), s);
        if (s.getGroupAdmin() != null) passportIdSet.add(s.getGroupAdmin().getPassportID());
    }

    public void insert(String name, double x, float y, int studentCount,
                    FormOfEducation formOfEducation, Semester semester, Person groupAdmin) throws DuplicateException{
        int id;
        if (collection.isEmpty()) id = 1;
        else id = Collections.max(collection.keySet()) + 1;
        Coordinates coordinates = StudyGroupParser.parseCoordinates(x,y);
        StudyGroup s = new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        add(s);
    }

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
