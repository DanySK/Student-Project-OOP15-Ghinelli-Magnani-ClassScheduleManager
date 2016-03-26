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
        for(final Teaching t : this.teachings) {
            if(t.equals(subject)){
                throw new IllegalArgumentException();
            }
        }
        this.teachings.add(subject);
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
