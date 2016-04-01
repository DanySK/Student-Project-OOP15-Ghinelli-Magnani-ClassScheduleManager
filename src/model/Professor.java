/**
 * 
 */
package model;

/**
 * @author Marti
 * The Professor class shapes the object that will identify the figure of Professor 
 *
 */
public class Professor{
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
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equalsIgnoreCase(other.surname))
            return false;
        return true;
    }
    
}
