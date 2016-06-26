/**
 * 
 */
package model_interface;
import model.Day;
import model.Hour;
import model.Semester;

/**
 * Interface that represents a lesson used by the model
 * 
 * @author Martina Magnani
 *
 */
public interface ILesson extends java.io.Serializable {
    /**
     * Getting method of prof
     * @return 
     *          Professor of the lesson
     */
    IProfessor getProfessor();
    
    /**
     * Setting method of prof
     * @param prof
     *          the new prof
     */
    void setProfessor(final IProfessor prof );
    
    /**
     * Getting method of teaching
     * @return
     *          teaching of the lesson
     */
    ITeaching getSubject();
    
    /**
     * Setting method of teaching
     * @param teaching
     */
    void setSubject(final ITeaching teaching);
    
    /**
     * Getting method of semester
     * @return
     *          semester of the lesson
     */Semester getSemester();
    
     /**
      * 
      * @param semester
      */
     void setSemester(final Semester semester);
    
    /**
     * Getting method of the classroom
     * @return
     *          ClassRoom
     */
    String getClassRoom();
    
    /**
     * 
     * @param classroom
     */
    void setClassRoom(final String classroom);
    
    /**
     * Getting method of the Hour
     * @return
     *          Hour
     */
    Hour getHour();
    
    /**
     * 
     * @param hour
     */
    void setHour(final Hour hour);
    
    /**
     * Getting method of the day
     * @return
     *          Day
     */
    Day getDay();
    
    /**
     * 
     * @param day
     */
    void setDay(final Day day);
    
    /**
     * Getting method of the duration 
     * @return
     *          duration (int)
     */
    int getDuration();
    
    /**
     * 
     * @param duration
     */
    void setDuration(final int duration);
    
    /**
     * Getting method of the id of the lesson
     * @return
     *          id
     */
    int getID();
}
