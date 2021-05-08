package sudyar.data;

public enum FormOfEducation {

    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;

    public static String nameList() {
        String nameList = "";
        for (FormOfEducation formOfEducation : values()) {
            nameList += formOfEducation.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);

    }
}
