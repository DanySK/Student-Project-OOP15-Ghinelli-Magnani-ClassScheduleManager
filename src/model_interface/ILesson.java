/**
 * 
 */
package model_interface;

import model.ClassRoom;
import model.Day;
import model.Hour;
import model.Professor;
import model.Teaching;

/**
 * @author Martina Magnani
 *
 */
public interface ILesson {
    public Professor getProfessor();
    public Teaching getSubject();
    public ClassRoom getClassRoom();
    public Hour getHour();
    public Day getDay();
    public int getDuration();
    public int getID();
}
