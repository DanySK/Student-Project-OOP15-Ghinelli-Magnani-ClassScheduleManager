/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marti
 * The ProfessorsList class allows you to build a list of professors and return it 
 */
public class ProfessorsList {
    private final List<Professor> professors;
    /**
     * Constructor of the list of professors
     */
    public ProfessorsList(){
        this.professors = new ArrayList<Professor>();
    }
    /**
     * Method that adds a professor in the list professors
     * @param prof
     *          the new professor
     */
    public void addProfessor(final Professor prof){
        if (!this.professors.contains(prof)) {
            this.professors.add(prof);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that returns the list professors
     * @return
     *         the complete list of professors
     */
    public List<Professor> getProfessorsList(){
        return this.professors;
    }
}
    