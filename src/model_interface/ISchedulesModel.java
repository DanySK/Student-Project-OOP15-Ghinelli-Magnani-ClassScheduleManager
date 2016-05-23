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
    Professor addProfessor(final String name);
    List<Professor> getProfessorsList();
    void addTeaching(final String name, final String year, final Court court);
    List<Teaching> getTeachingsList();
    void addClassroom(final String name);
    List<String> getClassroomsList();
    // void addYears(final String name);
    // List<String> getYearsList();
    void addLesson(final Professor prof, final Teaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration);
    void addLesson(final String prof, final Teaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration);
    Professor getProfessor(final String prof);
    boolean deleteLesson(final Lesson lesson);
    List<Lesson> getLessons(final String prof, final String teaching, final String year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day);
    List<Professor> getProfessorsActive();
    List<Teaching> getTeachingActive();
    List<String> getClassRoomActive();
}
