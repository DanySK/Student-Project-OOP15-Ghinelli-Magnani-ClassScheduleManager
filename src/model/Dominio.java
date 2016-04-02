/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all the lists useful to the application that are: a list of professors, a list of subjects and a list of lessons.
 * In all lists can be added new (respective) objects through various "add" methods.
 * All lists can be transferred with respective "get" methods.
 * The getLessons method contains the filters through which you can make various searches of the lessons.
 * 
 * @author Martina Magnani
 *
 */
public class Dominio {
    private final List<Professor> professorsList;
    private final List<Teaching> teachingsList;
    private final List<Lesson> lessonsList;
    /**
     * Constructor of class Dominio
     */
    public Dominio() {
        this.professorsList = new ArrayList<>();
        this.teachingsList = new ArrayList<>();
        this.lessonsList = new ArrayList<>();
    }
    /**
     * Method that adds a professor in the list professors
     * @param prof
     *          the new professor
     */
    public void addProfessor(final Professor prof){
        if (!this.professorsList.contains(prof)) {
            this.professorsList.add(prof);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that returns the list professors
     * @return
     *         the complete list of professors
     */
    public List<Professor> getProfessorsList(){
        return this.professorsList;
    }
    /**
     * Method that adds a subject in the list of teachings
     * @param subject
     *          the new subject
     */
    public void addTeaching(final Teaching subject){
        if (!this.teachingsList.contains(subject)) {
            this.teachingsList.add(subject);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that returns the list of teachings
     * @return
     *         the complete list of subjects
     */
    public List<Teaching> getTeachingsList(){
        return this.teachingsList;
    }
    /**
     * Method that add a lesson in the list of lessons
     * @param lesson
     *          the new lesson
     */
    public void addLesson(final Lesson lesson) {
        if (!this.professorsList.contains(lesson.getProfessor())) {
            this.professorsList.add(lesson.getProfessor());
        }
        if (!this.teachingsList.contains(lesson.getSubject())) {
            this.teachingsList.add(lesson.getSubject());
        }
        if (!this.lessonsList.contains(lesson)) {
            this.lessonsList.add(lesson);
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that delate a lesson in the list of lesson
     * @param lesson
     *          the lesson to delate
     * @return
     *          true 
     *                  if the lesson has been eliminated
     *          false
     *                  if lesson does not exist
     */
    public boolean delateLesson(final Lesson lesson) {
        if (this.lessonsList.contains(lesson)) {
            return this.lessonsList.remove(lesson);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
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
    public List<Lesson> getLessons(final Professor prof, final Teaching teaching, final ClassRoom classroom, final Hour hour, final Day day) {
        List<Lesson> finalList = new ArrayList<Lesson>();
        for (final Lesson l : this.lessonsList) {
            if(prof!=null && !l.getProfessor().equals(prof)){
                continue;
            }
            if (teaching.getTeaching()!=null && !l.getSubject().getTeaching().equals(teaching.getTeaching())) {
                continue;
            }
            if (teaching.getYear()!=null && !l.getSubject().getYear().equals(teaching.getYear())) {
                continue;
            }
            if (classroom!=null && !l.getClassRoom().equals(classroom)) {
                continue;
            }
            if (hour!=null && !l.getHour().equals(hour)) {
                continue;
            }
            if (day!=null && !l.getDay().equals(day)) {
                continue;
            }
            finalList.add(l);
        }
        return finalList;
    }
    /**
     * 
     * Method that allows to obtain the list of the only professors who have active lessons
     * @return
     *          list of professor
     */
    public List<Professor> getProfessorsActive(){
        final List<Professor> pActive = new ArrayList<>();
        for (final Lesson l : this.lessonsList) {
            if (pActive == null || !pActive.contains(l.getProfessor())) {
                pActive.add(l.getProfessor());
            }
        }
        return pActive;
    }
    /**
     * Method that allows to obtain the list of the only materials that are active in the lessons
     * @return
     *          list of teaching
     */
    public List<Teaching> getTeachingActive(){
        final List<Teaching> tActive = new ArrayList<>();
        for (final Lesson l : this.lessonsList) {
            if (tActive == null || !tActive.contains(l.getSubject())) {
                tActive.add(l.getSubject());
            }
        }
        return tActive;
    }
    /**
     * Method that allows to obtain the list of only classrooms occupied by some lessons
     * @return
     *          list of enum classroom
     */
    public List<ClassRoom> getClassRoomActive(){
        final List<ClassRoom> crActive = new ArrayList<>();
        for (final Lesson l : this.lessonsList) {
            if (crActive == null || !crActive.contains(l.getClassRoom())) {
                crActive.add(l.getClassRoom());
            }
        }
        return crActive;
    }
}
