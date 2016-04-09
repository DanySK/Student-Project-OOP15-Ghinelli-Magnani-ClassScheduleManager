/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import model_interface.IDominio;

/**
 * This class handles all the lists useful to the application that are: a list of professors, a list of subjects and a list of lessons.
 * In all lists can be added new (respective) objects through various "add" methods.
 * All lists can be transferred with respective "get" methods.
 * The getLessons method contains the filters through which you can make various searches of the lessons.
 * 
 * @author Martina Magnani
 *
 */
public class Dominio implements IDominio {
    private final List<Professor> professorsList;
    private final List<Teaching> teachingsList;
    private final List<ClassRoom> classroomsList;
    private final List<Lesson> lessonsList;
    private int counter;
    /**
     * Constructor of class Dominio
     */
    public Dominio() {
        this.professorsList = new ArrayList<>();
        this.teachingsList = new ArrayList<>();
        this.lessonsList = new ArrayList<>();
        this.classroomsList = new ArrayList<>();
        this.counter = 0;
    }
    /**
     * Method that adds a professor in the list professors
     * @param prof
     *          the new professor
     */
    public void addProfessor(final String name) {
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final Professor p : this.professorsList) {
            if (p.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.professorsList.add(new Professor(name));
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
    public void addTeaching(final String name, final Year year, final Court court){
        if (name==null || year==null || court==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final Teaching t : this.teachingsList) {
            if (t.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.teachingsList.add(new Teaching(name, year, court));
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
     * Method that adds a classroom in the list classrooms
     * @param prof
     *          the new classroom
     */
    public void addClassroom(final String name){
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final ClassRoom cl : this.classroomsList) {
            if (cl.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.classroomsList.add(new ClassRoom(name));
    }
    public List<ClassRoom> getClassroomsList() {
        return this.classroomsList;
    }
    /**
     * Method that add a lesson in the list of lessons
     * @param lesson
     *          the new lesson
     */
    public void addLesson(final Professor prof, final Teaching teaching, final Semester semester, final ClassRoom classroom, final Hour hour, final Day day, final int duration) {
        if (prof==null || teaching==null || semester==null || classroom==null || hour==null || day==null || duration<1) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        if (this.professorsList.contains(prof) && this.teachingsList.contains(teaching) && this.classroomsList.contains(classroom)) {
            for (final Lesson l : this.lessonsList) {
                if(l.getDay()==day && l.getClassRoom().equals(classroom) && l.getHour()==hour && l.getSemester()==semester){
                    throw new IllegalArgumentException();
                }
            }
            this.lessonsList.add(new Lesson(prof,teaching,semester,classroom,hour,day,duration, counter));
            this.counter++;
        }
        else {
            throw new IllegalArgumentException();       
        }
    }
    /**
     * Method that delete a lesson in the list of lesson
     * @param lesson
     *          the lesson to delete
     * @return
     *          true 
     *                  if the lesson has been eliminated
     *          false
     *                  if lesson does not exist
     */ 
    public boolean deleteLesson(final Lesson lesson) {
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
    public List<Lesson> getLessons(final String prof, final String teaching, final Year year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day) {
        List<Lesson> finalList = new ArrayList<Lesson>();
        for (final Lesson l : this.lessonsList) {
            if (prof!=null && !l.getProfessor().getName().equals(prof)){
                continue;
            }
            if (teaching!=null && !l.getSubject().getName().equals(teaching)) {
                continue;
            }
            if (year!=null && !l.getSubject().getYear().equals(year)) {
                continue;
            }
            if (court!=null && !l.getSubject().getCourt().equals(Court.COMUNE) && !l.getSubject().getCourt().equals(court)) {
                continue;
            }
            if (semester!=null && l.getSemester().equals(semester)) {
                continue;
            }
            if (classroom!=null && !l.getClassRoom().getName().equals(classroom)) {
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
        final List<ClassRoom> clActive = new ArrayList<>();
        for (final Lesson l : this.lessonsList) {
            if (clActive == null || !clActive.contains(l.getClassRoom())) {
                clActive.add(l.getClassRoom());
            }
        }
        return clActive;
    }
}
