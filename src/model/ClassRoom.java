/**
 * 
 */
package model;

/**
 * @author Marti
 * The class classroom model the object that will identify a classroom of a university 
 */
public class ClassRoom {
    private final String name;
    /**
     * Constructor of class ClassRoom
     * @param name
     *          name of the class
     */
    public ClassRoom(final String name) {
        this.name = name;
    }
    /**
     * Method that returns the name of the class
     * @return
     *    the name of the class
     */
    public String getClassRoom() {
        return this.name;
    }
}
