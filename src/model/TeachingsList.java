/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marti
 * The TeachingsList class allows you to build a list of subjects and return it 
 */
public class TeachingsList {
    private final List<Teaching> teachings;
    /**
     * Constructor of the list of subjects
     */
    
    public TeachingsList(){
        this.teachings = new ArrayList<Teaching>();
    }
    /**
     * Method that adds a subject in the list of teachings
     * @param subject
     *          the new subject
     */
    public void addTeaching(final Teaching subject){
        if (!this.teachings.contains(subject)) {
            this.teachings.add(subject);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that returns the list of teachings
     * @return
     *         the complete list of subjects
     */
    public List<Teaching> getTeachingsList(){
        return this.teachings;
    }
}
