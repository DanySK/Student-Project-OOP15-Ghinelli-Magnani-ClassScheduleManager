/**
 * 
 */
package model;

/**
 * @author Marti
 * The Teaching class shapes the object that will identify a university course 
 */
public class Teaching {
    private final String name;
    /**
     * Constructor of class Teaching
     * @param name
     *           the name of one of the university course
     */
    public Teaching(final String name){
        this.name = name;
    }
    /**
     * Method that returns the name of the university course
     * @return
     *          name of the course
     */
    public String getTeaching(){
        return this.name;
    }
    
    public boolean equals(final Teaching t){
        return this.getTeaching().equals(t.getTeaching());
    }
}
