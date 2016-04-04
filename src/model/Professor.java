/**
 * 
 */
package model;

import model_interface.IProfessor;

/**
 * The Professor class shapes the object that will identify the figure of Professor 
 * 
 * @author Martina Magnani
 */
public class Professor implements IProfessor{
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
        if(name == null || surname == null){
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        this.name = name.substring(0,1).toUpperCase();
        this.surname = surname.substring(1, surname.length()).toLowerCase();
    }
    /**
     * Method that returns the name of the professor
     * @return
     *          name of professor
     */
    public String getName() {
        return this.name;
    }
    /**
     * Method that returns the surname of the professor
     * @return
     *          surname of professor
     */
    public String getSurname() {
        return this.surname;
    }
    /**
     * 
     * @return 
     *          professor name and surname
     */
    public String toString() {
        return this.name + " " + this.surname;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
        Professor other = (Professor) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }
    
}
