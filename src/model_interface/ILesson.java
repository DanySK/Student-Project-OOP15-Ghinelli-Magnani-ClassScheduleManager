/**
 * 
 */
package model_interface;
import model.Day;
import model.Hour;
import model.Professor;
import model.Semester;
import model.Teaching;

/**
 * @author Martina Magnani
 *
 */
public interface ILesson extends java.io.Serializable {
    IProfessor getProfessor();
    ITeaching getSubject();
    Semester getSemester();
    String getClassRoom();
    Hour getHour();
    Day getDay();
    int getDuration();
    int getID();
}
