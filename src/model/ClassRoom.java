/**
 * 
 */
package model;

import model_interface.IClassRoom;

/**
 * @author Martina Magnani
 *
 */
public class ClassRoom implements IClassRoom{
    private final String name;

    public ClassRoom(final String name) {
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        this.name = name;
    }
    /**
     * 
     * @return 
     *          the complete name of the classroom.
     */
    public String getName() {
        return this.name;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ClassRoom other = (ClassRoom) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
