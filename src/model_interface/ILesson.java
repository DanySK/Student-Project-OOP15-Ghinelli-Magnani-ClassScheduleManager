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
public interface ILesson extends java.io.Serializable{
    public Professor getProfessor();
    public Teaching getSubject();
    public String getClassRoom();
    public Hour getHour();
    public Day getDay();
    public int getDuration();
    public int getID();
}
