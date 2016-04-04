/**
 * 
 */
package model_interface;

import java.util.List;

import model.ClassRoom;
import model.Day;
import model.Hour;
import model.Lesson;
import model.Professor;
import model.Teaching;
import model.Year;

/**
 * @author Martina Magnani
 *
 */
public interface IDominio {
    public void addProfessor(final String name, final String surname);
    public List<Professor> getProfessorsList();
    public void addTeaching(final String name, final Year year);
    public List<Teaching> getTeachingsList();
    public void addLesson(final Professor prof, final Teaching teaching, final ClassRoom classroom, final Hour hour, final Day day, final int duration);
    public boolean deleteLesson(final Lesson lesson);
    public List<Lesson> getLessons(final Professor prof, final Teaching teaching, final ClassRoom classroom, final Hour hour, final Day day);
    public List<Professor> getProfessorsActive();
    public List<Teaching> getTeachingActive();
    public List<ClassRoom> getClassRoomActive();
}
