/**
 * 
 */
package model_interface;

import java.util.List;
import model.Court;
import model.Day;
import model.Hour;
import model.Lesson;
import model.Professor;
import model.Semester;
import model.Teaching;
import model.Year;

/**
 * @author Martina Magnani
 *
 */
public interface ISchedulesModel extends java.io.Serializable {
    /**
     * Method that adds a professor in the list professors
     * @param name
     *          the new professor
     * @return Professor
     */
    IProfessor addProfessor(final String name);
    
    /**
     * Method that returns the list professors
     * @return
     *         the complete list of professors
     */
    List<IProfessor> getProfessorsList();
    
    /**
     * Method that adds a subject in the list of teachings
     * @param name
     *          the name of the new subject
     * @param year
     *          the year of the subject
     * @param cour
     *          the court of the subject
     */
    void addTeaching(final String name, final Year year, final Court court);
    
    /**
     * Method that returns the list of teachings
     * @return
     *         the complete list of subjects
     */
    List<ITeaching> getTeachingsList();
    
    /**
     * Method that adds a classroom in the list classrooms
     * @param prof
     *          the new classroom
     */
    void addClassroom(final String name);
    
    /**
     * Method that returns the list of classrooms
     * @return
     *         the complete list of class
     */
    List<String> getClassroomsList();
    
    /**
     * Method that add a lesson in the list of lessons
     * @param prof
     *          prof of the lesson
     * @param teaching
     *          teching of the lesson
     * @param semester
     *          semester of the lesson
     * @param classroom
     *          classroom of the lesson 
     * @param hour
     *          hour of the lesson  
     * @param day
     *          day of the lesson
     * @param duration
     *          duration of the lesson  
     */
    void addLesson(final IProfessor prof, final ITeaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration);
    
    /**
     * Method that add a lesson in the list of lessons
     * @param prof
     *          prof of the lesson
     * @param teaching
     *          teaching of the lesson
     * @param semester
     *          semester of the lesson
     * @param classroom
     *          classroom of the lesson 
     * @param hour
     *          hour of the lesson  
     * @param day
     *          day of the lesson
     * @param durata
     *          durata of the lesson  
     */
    void addLesson(final String prof, final ITeaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration);
    
    /**
     * Method that returns (if exists) the object Professor whose name matches the string prof
     * @param prof
     *          string with the name of prof
     * @return 
     *        Professor
     */
    IProfessor getProfessor(final String prof);
    
    /**
     * Method that delete a lesson in the list of lesson
     * @param lesson
     *          the lesson to delete
     * @return
     *          true 
     *                  if the lesson has been eliminated
     *          false
     *                  if lesson does not exist
     */
    boolean deleteLesson(final Lesson lesson);
    
    /**
     * Method that returns the list of filtered lessons according to the parameters that are passed
     * If these are NULL means that I do not want to filter according to those objects
     * @param prof
     *          parameter that if non-NULL indicates that I want the lessons of this particular prof
     * @param teaching
     *          parameter that if non-NULL indicates that I want the lessons of this particular teaching
     * @param classroom
     *          parameter that if non-NULL indicates that I want the lessons conducted in this particular classroom
     * @param hour
     *          parameter that if non-NULL indicates that I want the lessons conducted in this particular hour
     * @param day
     *          parameter that if non-NULL indicates that I want the lessons conducted in this particular day
     * @return
     */
    List<ILesson> getLessons(final String prof, final String teaching, final Year year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day);
    
    /**
     * 
     * Method that allows to obtain the list of the only professors who have active lessons
     * @return
     *          list of professor
     */
    List<IProfessor> getProfessorsActive();

    /**
     * Method that allows to obtain the list of the only materials that are active in the lessons
     * @return
     *          list of teaching
     */
    List<ITeaching> getTeachingActive();

    /**
     * Method that allows to obtain the list of only classrooms occupied by some lessons
     * @return
     *          list of classroom
     */
    List<String> getClassRoomActive();
}
