/**
 * 
 */
package model;

import java.io.Serializable;

/** The Lesson class shapes the object that will identify an unique lesson
 * @author Marti
 *
 */
public class Lesson implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Professor prof;
    private final Teaching teaching;
    private final AccademicYear year;
    private final ClassRoom classroom;
    private final Hour hour;
    private final Day day;
    private final int duration;
    /**
     * Constructor of the class Lesson
     * @param prof
     *          prof holding the lesson
     * @param teaching
     *          teaching must lesson
     * @param year
     *          academic year in which is held the lesson 
     * @param classroom
     *          class in which is held the lesson
     * @param hour
     *          schedule
     * @param day
     *          day of the week
     * @param duration
     *          lesson duration
     */
    public Lesson(final Professor prof, final Teaching teaching, final AccademicYear year, final ClassRoom classroom, final Hour hour, final Day day, final int duration) {
        this.prof = prof;
        this.teaching = teaching;
        this.year = year;
        this.classroom = classroom;
        this.hour = hour;
        this.day = day;
        this.duration = duration;
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
    public Teaching getTeaching() {
        return this.teaching;
    }
    /**
     * Getting method of the year
     * @return
     *          Academic Year
     */
    public AccademicYear getAccademicYear() {
        return this.year;
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
    
    public boolean equals(final Lesson l){
        return this.getClassRoom().equals(l.getClassRoom()) && this.getHour() == l.getHour() && this.getDay() == (l.getDay());
    }
    
    /**
     * Method that represents the "lesson" 
     * @return 
     *          string
     */
    public String toString() {
        return "Prof: " + this.prof + " | " 
                + "Teaching: " + this.teaching + " | " 
                + "Accademic Year: " + this.year + " | "
                + "ClassRoom: " + this.classroom + " | "
                + "Day: " + this.day + " | "
                + "Hour: " + this.hour + " | "
                + "Duration: " + this.duration + " | ";
    }
}
