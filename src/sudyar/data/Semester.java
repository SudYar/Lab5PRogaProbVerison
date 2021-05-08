package sudyar.data;

public enum Semester {

    FIRST,
    FIFTH,
    SEVENTH;

    public static String nameList() {
        String nameList = "";
        for (Semester semester : values()) {
            nameList += semester.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);

    }
}

