/**
 * 
 */
package model;

/**
 * @author Marti
 * The class Academic Year model the object that will identify one academic year 
 */
public class AccademicYear {
    private final String year;
    /**
     * Constructor of class AccademicYear
     * @param name
     *          the academic year of add
     */
    public AccademicYear(final String name){
        this.year = name;
    }
    /**
     * Method that returns the name of the year
     * @return
     *          name of this year
     */
    public String getAccademicYear(){
        return this.year;
    }
    
    public boolean equals(final AccademicYear y){
        return this.getAccademicYear().equals(y.getAccademicYear());
    }
}
