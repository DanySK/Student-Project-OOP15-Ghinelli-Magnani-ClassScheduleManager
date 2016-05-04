package view.utility;

public enum AddFields {

    NAME("Name", false),
    PROF("Prof.", true),
    DAY("Day", false),
    CLASS("Class", false),
    HOUR("Hour", false),
    DURATION("Duration", true),
    YEAR("Year", false);
    
    private final String name;
    private final boolean bool;

    AddFields(final String nomination, final boolean value) {
        this.name = nomination;
        this.bool = value;
    }
    
    public static String getName(final int i) {
        return AddFields.values()[i].name;
    }
    
    public static boolean getBool(final int i) {
        return AddFields.values()[i].bool;
    }
}
