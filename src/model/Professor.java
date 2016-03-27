/**
 * 
 */
package model;

/**
 * @author Marti
 * The Professor class shapes the object that will identify the figure of Professor 
 *
 */
public class Professor {
    private final String name;
    private final String surname;
    /**
     * Constructor of class Professor
     * @param name
     *            name of professor
     * @param surname
     *            surname of professor
     */
    public Professor(final String name, final String surname) {
        this.name = name;
        this.surname = name;
    }
    /**
     * Method that returns the name and surname of the professor
     * @return 
     *          professor name and surname
     */
    public String getProfessor() {
        return this.name + " " + this.surname;
    }
    
    public boolean equals(final Professor p){
        return this.getProfessor().equals(p.getProfessor());
    }
}
