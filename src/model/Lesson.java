/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Optional;

import model_interface.ILesson;

/** The Lesson class shapes the object that will identify an unique lesson
 * 
 * @author Martina Magnani
 *
 */
public class Lesson implements ILesson, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Professor prof;
    private final Teaching teaching;
    private final Semester semester;
    private final ClassRoom classroom;
    private final Hour hour;
    private final Day day;
    private final int duration;
    private int id;
    /**
     * Constructor of the class Lesson
     * @param prof
     *          prof holding the lesson
     * @param teaching
     *          teaching must lesson 
     * @param classroom
     *          class in which is held the lesson
     * @param hour
     *          schedule
     * @param day
     *          day of the week
     * @param duration
     *          lesson duration
     */
    public Lesson(final Professor prof, final Teaching teaching, final Semester semester, final ClassRoom classroom, final Hour hour, final Day day, final int duration, final int id) {
        if(prof == null || teaching == null || semester == null || classroom == null || hour == null || day == null || duration == 0){
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        this.prof = prof;
        this.teaching = teaching;
        this.semester = semester;
        this.classroom = classroom;
        this.hour = hour;
        this.day = day;
        this.duration = duration;
        this.id = id;
    }
    /**
     * Getting method of prof
     * @return 
     *          Professor of the lesson
     */
    public Professor getProfessor() {
        return this.prof;
    }
    /**
     * Getting method of teaching
     * @return
     *          Teaching of the lesson
     */
    public Teaching getSubject() {
        return this.teaching;
    }
    /**
     * Getting method of semester
     * @return
     *          semester of the lesson
     */
    public Semester getSemester() {
        return this.semester;
    }
    /**
     * Getting method of the classroom
     * @return
     *          ClassRoom
     */
    public ClassRoom getClassRoom() {
        return this.classroom;
    }
    /**
     * Getting method of the Hour
     * @return
     *          Hour
     */
    public Hour getHour() {
        return this.hour;
    }
    /**
     * Getting method of the day
     * @return
     *          Day
     */
    public Day getDay() {
        return this.day;
    }
    /**
     * Getting method of the duration 
     * @return
     *          duration (int)
     */
    public int getDuration() {
        return this.duration;
    }
    /**
     * Getting method of the id of the lesson
     * @return
     *          id
     */
    public int getID(){
        return this.id;
    }
    /**
     * Method that represents the "lesson" 
     * @return 
     *          string
     */
    public String toString() {
        return "Prof: " + this.prof + " | " 
                + "Teaching: " + this.teaching.toString() + " | " 
                + "Semester: " + this.semester + " | "            
                + "ClassRoom: " + this.classroom + " | "
                + "Day: " + this.day + " | "
                + "Hour: " + this.hour + " | "
                + "Duration: " + this.duration + " | ";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((hour == null) ? 0 : hour.hashCode());
        result = prime * result + ((semester == null) ? 0 : semester.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        if (classroom == null) {
            if (other.classroom != null)
                return false;
        } else if (!classroom.equals(other.classroom))
            return false;
        if (day != other.day)
            return false;
        if (hour != other.hour)
            return false;
        if (semester != other.semester)
            return false;
        return true;
    }
}
