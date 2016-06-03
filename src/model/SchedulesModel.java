/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model_interface.ILesson;
import model_interface.IProfessor;
import model_interface.ISchedulesModel;
import model_interface.ITeaching;

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
    private final List<IProfessor> professorsList;
    private final List<ITeaching> teachingsList;
    private final List<String> classroomsList;
    private final List<ILesson> lessonsList;
    private int counter;
    
    public SchedulesModel() {
        this.professorsList = new ArrayList<>();
        this.teachingsList = new ArrayList<>();
        this.lessonsList = new ArrayList<>();
        this.classroomsList = new ArrayList<>();
        this.counter = 0;
        System.out.println("schedulesmodel");

    }
    
    @Override
    public IProfessor addProfessor(final String name) throws IllegalArgumentException {
        if (name==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final IProfessor p : this.professorsList) {
            if (p.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        final IProfessor prof = new Professor(name);
        this.professorsList.add(prof);
        return prof;
    }
    
    @Override
    public List<IProfessor> getProfessorsList() {
        final List<IProfessor> difensiveListProfessor = this.professorsList;
        return difensiveListProfessor;
    }
    
    @Override
    public void addTeaching(final String name, final Year year, final Court court) throws IllegalArgumentException {
        if (name==null || year==null || court==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        for (final ITeaching t : this.teachingsList) {
            if (t.getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }
        this.teachingsList.add(new Teaching(name, year, court));
    }
    
    @Override
    public List<ITeaching> getTeachingsList() {
        final List<ITeaching> difensiveListTeaching = this.teachingsList;
        return difensiveListTeaching;
    }
    
    @Override
    public void addClassroom(final String name) throws IllegalArgumentException {
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
    
    @Override
    public List<String> getClassroomsList() {
        return this.classroomsList;
    }

    @Override
    public void addLesson(final IProfessor prof, final ITeaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration) throws IllegalArgumentException, NoSuchElementException {
        if (prof==null || teaching==null || semester==null || classroom==null || hour==null || day==null || duration<1) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        if (this.professorsList.contains(prof) && this.teachingsList.contains(teaching) && this.classroomsList.contains(classroom)) {
            for (final ILesson l : this.lessonsList) {
                if (l.getClassRoom().equals(classroom) && l.getDay()==day && l.getHour()==hour && l.getSemester()==semester) {
                    throw new IllegalArgumentException("The classroom is already used!");
                }
                if (l.getProfessor().equals(prof) && l.getDay()==day && l.getHour()==hour && l.getSemester()==semester) {
                    throw new IllegalArgumentException("The professor is already engaged!");
                }
            }
            this.lessonsList.add(new Lesson(prof,teaching,semester,classroom,hour,day,duration, counter));
            this.counter++;
        }
        else {
            throw new NoSuchElementException("");       
        }
        System.out.println("Ho aggiunto una lezione con semestre uguale a: " + semester);
    }
    
    @Override
    public void addLesson(final String prof, final ITeaching teaching, final Semester semester, final String classroom, final Hour hour, final Day day, final int duration) throws IllegalArgumentException {
        if (prof==null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }
        IProfessor professor = this.getProfessor(prof);
        if (professor==null) {
            professor = this.addProfessor(prof);
        }
        this.addLesson(professor, teaching, semester, classroom, hour, day, duration);
    }
    
    public void addLesson(final ILesson l) throws IllegalArgumentException {
        if (l == null) {
            throw new IllegalArgumentException("The values can't be null!"); 
        }            
        this.addLesson(l.getProfessor(), l.getSubject(), l.getSemester(), l.getClassRoom(), l.getHour(), l.getDay(), l.getDuration());
    }
    
    @Override
    public IProfessor getProfessor(final String prof) {
        for (final IProfessor p : this.professorsList) {
            if (p.getName().equals(prof)) {
                return p;
            }
        }
        return null;
    }
        
    @Override 
    public boolean deleteLesson(final ILesson lesson) throws NoSuchElementException {
        if (this.lessonsList.contains(lesson)) {
            return this.lessonsList.remove(lesson);
        }
        else {
            throw new NoSuchElementException("The lesson isn't present!");
        }
    }
    
    @Override
    public List<ILesson> getLessons(final String prof, final String teaching, final Year year, final Court court, final Semester semester, final String classroom, final Hour hour, final Day day) {
        List<ILesson> finalList = new ArrayList<>();
        for (final ILesson l : this.lessonsList) {
            if (prof!=null && !l.getProfessor().getName().equals(prof)) {
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
            if (semester!=null && !l.getSemester().equals(semester)) {
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
        System.out.println("Sto tornando la lezione con semestre uguale a: " + semester);
        return finalList;
    }
    
    @Override
    public List<IProfessor> getProfessorsActive() {
        final List<IProfessor> pActive = new ArrayList<>();
        for (final ILesson l : this.lessonsList) {
            if (pActive == null || !pActive.contains(l.getProfessor())) {
                pActive.add(l.getProfessor());
            }
        }
        return pActive;
    }
    
    @Override
    public List<ITeaching> getTeachingActive() {
        final List<ITeaching> tActive = new ArrayList<>();
        for (final ILesson l : this.lessonsList) {
            if (tActive == null || !tActive.contains(l.getSubject())) {
                tActive.add(l.getSubject());
            }
        }
        return tActive;
    }
    
    @Override
    public List<String> getClassRoomActive() {
        final List<String> clActive = new ArrayList<>();
        for (final ILesson l : this.lessonsList) {
            if (clActive == null || !clActive.contains(l.getClassRoom())) {
                clActive.add(l.getClassRoom());
            }
        }
        return clActive;
    }

    @Override
    public boolean checkChanges(List<ILesson> lessonModified) throws IllegalArgumentException {
        if (lessonModified != null) {
            List<ILesson> tempLesson = new ArrayList<>();
            for (final ILesson l : lessonModified) {
                ILesson lesson = this.getLesson(l.getID());
                if (lesson != null) {
                    tempLesson.add(lesson);
                }
            }
            for (final ILesson l : lessonModified) {
                    this.deleteLesson(l);
            }
            try {
                 for (final ILesson l : lessonModified) {
                      this.addLesson(l);
                 }
            }catch (IllegalArgumentException e2) {
                for (final ILesson l : tempLesson) {
                    this.addLesson(l);
                }                
                throw e2;
            }
             return true;
        }
        return false;
    }
    
    @Override
    public ILesson getLesson(final int id) {
        for (final ILesson l: this.lessonsList) {
             if (l.getID() == id) {
                 return l;
             }  
        }
        return null;
    }
}
