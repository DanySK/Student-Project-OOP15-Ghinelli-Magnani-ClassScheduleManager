package view_utility;

public enum AddFields {

    NAME("Name"),
    PROF("Prof."),
    DAY("Day"),
    CLASS("Class"),
    HOUR("Hour"),
    DURATION("Duration"),
    YEAR("Year");
    
    private final String name;

    AddFields(final String nomination) {
        this.name = nomination;
    }
    
    public static String getName(final int i) {
        return AddFields.values()[i].name;
    }
}
