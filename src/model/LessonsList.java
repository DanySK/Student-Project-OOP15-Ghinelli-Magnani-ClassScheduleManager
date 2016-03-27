/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marti
 *
 */
public class LessonsList {
    private final List<Lesson> lessons;
    
    public LessonsList() {
        this.lessons = new ArrayList<Lesson>();
    }
    
    public void addLesson(final Lesson lesson) {
        this.lessons.add(lesson);
    }
    
    public boolean delateLesson(final Lesson lesson) {
        if (this.lessons.contains(lesson)) {
            return this.lessons.remove(lesson);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    public List<Lesson> getLessons(final Professor prof, final Teaching teaching, final AccademicYear year, final ClassRoom classroom, final Hour hour, final Day day) {
        List<Lesson> finalList = new ArrayList<Lesson>();
        for (final Lesson l : this.lessons) {
            if(prof!=null && !l.getProfessor().equals(prof)){
                continue;
            }
            if(teaching!=null && !l.getTeaching().equals(teaching)){
                continue;
            }
            if(year!=null && !l.getAccademicYear().equals(year)){
                continue;
            }
            if(classroom!=null && !l.getClassRoom().equals(classroom)){
                continue;
            }
            if(hour!=null && !l.getHour().equals(hour)){
                continue;
            }
            if(day!=null && !l.getDay().equals(day)){
                continue;
            }
            finalList.add(l);
        }
        return finalList;
    }
  
}
