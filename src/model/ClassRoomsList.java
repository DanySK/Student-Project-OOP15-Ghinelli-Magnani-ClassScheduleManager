/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marti
 * The ClassRoomsList class allows you to build a list of classrooms and return it
 */
public class ClassRoomsList {
    private final List<ClassRoom> classroomslist;
    /**
     * Constructor of the list of classrooms
     */
    public ClassRoomsList(){
        this.classroomslist = new ArrayList<ClassRoom>();
    }
    /**
     * Method that adds a classroom in the list of classrooms
     * @param classroom
     *          the new academic classroom
     */
    public void addClassRoom(final ClassRoom classroom){
        if (!this.classroomslist.contains(classroom)) {
            this.classroomslist.add(classroom);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that returns the list of classrooms
     * @return
     *         the complete list of classrooms
     */
    public List<ClassRoom> getAccademicYearsList(){
        return this.classroomslist;
    }
}
