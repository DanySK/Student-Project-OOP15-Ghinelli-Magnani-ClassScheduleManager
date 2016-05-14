/**
 * 
 */
package model_interface;

import java.util.List;

import model.ClassRoom;
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
public interface ISchedulesModel {
    public Professor addProfessor(final String name);
    public List<Professor> getProfessorsList();
    public void addTeaching(final String name, final Year year, final Court court);
    public List<Teaching> getTeachingsList();
    public void addClassroom(final String name);
    public List<String> getClassroomsList();
    public void addLesson(final Professor prof, final Teaching teaching, final Semester semester, final ClassRoom classroom, final Hour hour, final Day day, final int duration);
    public void addLesson(final String prof, final Teaching teaching, final Semester semester, final ClassRoom classroom, final Hour hour, final Day day, final int duration);
    public Professor getProfessor(final String prof);
    public boolean deleteLesson(final Lesson lesson);
    public List<Lesson> getLessons(final String prof, final String teaching, final Year year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day);
    public List<Professor> getProfessorsActive();
    public List<Teaching> getTeachingActive();
    public List<ClassRoom> getClassRoomActive();
}
