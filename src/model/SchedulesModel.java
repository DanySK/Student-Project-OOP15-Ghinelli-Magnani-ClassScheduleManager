/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model_interface.ISchedulesModel;

/**
 * This class handles all the lists useful to the application that are: a list of professors, a list of subjects and a list of lessons.
 * In all lists can be added new (respective) objects through various "add" methods.
 * All lists can be transferred with respective "get" methods.
 * The getLessons method contains the filters through which you can make various searches of the lessons.
 * 
 * @author Martina Magnani
 *
 */
public class SchedulesModel implements ISchedulesModel {
    private static final long serialVersionUID = 1L;
    private final List<Professor> professorsList;
    private final List<Teaching> teachingsList;
    private final List<String> classroomsList;
    private final List<String> academicYears;
    private final List<Lesson> lessonsList;
    private int counter;
    /**
     * Constructor of class SchedulesModel
     */
    public SchedulesModel() {
        this.professorsList = new ArrayList<>();
        this.teachingsList = new ArrayList<>();
        this.lessonsList = new ArrayList<>();
        this.classroomsList = new ArrayList<>();
        this.academicYears = new ArrayList<>();
        this.counter = 0;
        System.out.println("schedulesmodel");

    }
    /**
     * Method that adds a professor in the list professors
     * @param prof
     *          the new professor
     */
    public Professor addProfessor(final String name) {
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final Professor p : this.professorsList) {
            if (p.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        final Professor prof = new Professor(name);
        this.professorsList.add(prof);
        return prof;
    }
    /**
     * Method that returns the list professors
     * @return
     *         the complete list of professors
     */
    public List<Professor> getProfessorsList(){
        final List<Professor> difensiveListProfessor = this.professorsList;
        return difensiveListProfessor;
    }
    /**
     * Method that adds a subject in the list of teachings
     * @param subject
     *          the new subject
     */
    public void addTeaching(final String name, final String year, final Court court){
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
        final List<Teaching> difensiveListTeaching = this.teachingsList;
        return difensiveListTeaching;
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
        for (final String s : this.classroomsList) {
            if (s.equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.classroomsList.add(name);
    }
    /**
     * Method that returns the list of classrooms
     * @return
     *         the complete list of class
     */
    public List<String> getClassroomsList() {
        return this.classroomsList;
    }
    /**
     * Method that adds a classroom in the list academic years
     * @param prof
     *          the new years
     */
    public void addYears(final String name){
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final String s : this.academicYears) {
            if (s.equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.academicYears.add(name);
    }
    /**
     * Method that returns the list of academic year
     * @return
     *         the complete list of class
     */
    public List<String> getYearsList() {
        System.out.println(this.academicYears);
        return this.academicYears;   
    }

    /**
     * Method that add a lesson in the list of lessons
     * @param prof
     *          prof of the lesson
     * @param teaching
     *          teching of the lesson
     * @param semester
     *          semester of the lesson
     * @param classroom
     *          classroom of the lesson 
     * @param hour
     *          hour of the lesson  
     * @param day
     *          day of the lesson
     * @param durata
     *          durata of the lesson  
     */
    public void addLesson(final Professor prof, final Teaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration) {
        if (prof==null || teaching==null || semester==null || classroom==null || hour==null || day==null || duration<1) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        if (this.professorsList.contains(prof) && this.teachingsList.contains(teaching) && this.classroomsList.contains(classroom)) {
            for (final Lesson l : this.lessonsList) {
                if (l.getDay()==day && l.getClassRoom().equals(classroom) && l.getHour()==hour && l.getSemester()==semester) {
                    throw new IllegalArgumentException();
                }
                if (l.getProfessor().equals(prof) && l.getDay()==day && l.getHour()==hour && l.getSemester()==semester) {
                    throw new IllegalArgumentException();
                }
            }
            this.lessonsList.add(new Lesson(prof,teaching,semester,classroom,hour,day,duration, counter));
            this.counter++;
        }
        else {
            throw new NoSuchElementException();       
        }
    }
    /**
     * Method that add a lesson in the list of lessons
     * @param prof
     *          prof of the lesson
     * @param teaching
     *          teaching of the lesson
     * @param semester
     *          semester of the lesson
     * @param classroom
     *          classroom of the lesson 
     * @param hour
     *          hour of the lesson  
     * @param day
     *          day of the lesson
     * @param durata
     *          durata of the lesson  
     */
    public void addLesson(final String prof, final Teaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration) {
        if (prof==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        Professor professor = this.getProfessor(prof);
        if (professor==null) {
            professor = this.addProfessor(prof);
        }
        this.addLesson(professor, teaching, semester, classroom, hour, day, duration);
    }
    /**
     * Method that returns (if exists) the object Professor whose name matches the string prof
     * @param prof
     *          string with the name of prof
     * @return 
     *        Professor
     */
    public Professor getProfessor(final String prof) {
        for (final Professor p : this.professorsList) {
            if (p.getName().equals(prof)) {
                return p;
            }
        }
        return null;
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
    public List<Lesson> getLessons(final String prof, final String teaching, final String year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day) {
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
     *          list of classroom
     */
    public List<String> getClassRoomActive(){
        final List<String> clActive = new ArrayList<>();
        for (final Lesson l : this.lessonsList) {
            if (clActive == null || !clActive.contains(l.getClassRoom())) {
                clActive.add(l.getClassRoom());
            }
        }
        return clActive;
    }
}
