/**
 * 
 */
package model;

/**
 * @author Marti
 * The Teaching class shapes the object that will identify a university course 
 */
public class Teaching{
    private final String name;
    private final Year year;
    /**
     * Constructor of class Teaching
     * @param name
     *           the name of one of the university course
     */
    public Teaching(final String name, final Year year){
        if(name == null || year == null){
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        this.name = name;
        this.year = year;
    }
    /**
     * Method that returns the name of the university course
     * @return
     *          name of the course
     */
    public String getTeaching(){
        return this.name;
    }
    /**
     * Method that returns the year in which this subject is taught
     * @return
     *         year
     */
    public Year getYear(){
        return this.year;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teaching other = (Teaching) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        if (year != other.year)
            return false;
        return true;
    }
    
    public String toString() {
        return "Subject Name: " + this.name + " | " + this.year;
    }
}
