/**
 * 
 */
package model_interface;
import model.Day;
import model.Hour;
import model.Professor;
import model.Teaching;

/**
 * @author Martina Magnani
 *
 */
public interface ILesson extends java.io.Serializable {
    Professor getProfessor();
    Teaching getSubject();
    String getClassRoom();
    Hour getHour();
    Day getDay();
    int getDuration();
    int getID();
}
