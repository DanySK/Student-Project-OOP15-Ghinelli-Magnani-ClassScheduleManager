/**
 * 
 */
package model;

/**
 * * The enum Day has the task of model the days of the week 
 * 
 * @author Marti
 */
public enum Day {
    /**
     * Monday
     */
    MONDAY("Monday"),
    /**
     * Tuesday
     */
    TUESDAY("Tuesday"),
    /**
     * Wednesday
     */
    WEDNESDAY("Wednesday"),
    /**
     * Thursday
     */
    THURSDAY("Thursday"),
    /**
     * Friday
     */
    FRIDAY("Friday");

    private final String name;
    
    private Day(final String s) {
            name = s;
    }
    /**
     * 
     * @return 
     *          string containing the name of the day.
     */
    public String getName() {
            return name;
    }
}
