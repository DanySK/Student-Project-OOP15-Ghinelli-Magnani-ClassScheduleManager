/**
 * 
 */
package model;

/**
 * @author Martina Magnani
 *
 */
public enum Semester {
    /**
     * First semester
     */
    FIRST_SEMESTER("1° Semester"),
    /**
     * Second semester
     */
    SECOND_SEMESTER("2° Semester");
    
    private final String name;
    
    private Semester(final String s) {
            name = s;
    }
    /**
     * 
     * @return 
     *          string containing the name of the semester.
     */
    public String getName() {
            return name;
    }
}
