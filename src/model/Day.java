/**
 * 
 */
package model;

/**
 * * The enum Day has the task of model the days of the week 
 * 
 * @author Martina Magnani
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

    private final String day;
    
    private Day(final String s) {
            day = s;
    }
    /**
     *
     * @return 
     *          string containing the name of the day.
     */
    public String getDay() {
            return this.day;
    }
}
