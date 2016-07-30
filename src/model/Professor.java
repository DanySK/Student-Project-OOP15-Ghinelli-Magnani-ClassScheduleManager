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
public class Professor implements IProfessor {
    private static final long serialVersionUID = 1L;
    private final String name_surname;
    
    public Professor(final String name) {
        if(name == null){
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        this.name_surname = name;
    }
    
    @Override
    public String getName() {
        return this.name_surname;
    }

    @Override
    public String toString() {
        return this.name_surname;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name_surname == null) ? 0 : name_surname.hashCode());
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
        if (name_surname == null) {
            if (other.name_surname != null)
                return false;
        } else if (!name_surname.equals(other.name_surname))
            return false;
        return true;
    }
  
    
}
