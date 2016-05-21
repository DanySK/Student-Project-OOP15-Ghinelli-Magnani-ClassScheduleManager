package view.utility;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.Day;
import model.Hour;
import model.Lesson;

public final class StructCreatorUtility {
    
    private static final int TYPE1 = 0;
    private static final int TYPE2 = 1;
    private static final int EMPTY = 9;
    
    private StructCreatorUtility() {
        
    }

    public static List<List<Object>> getStruct(final int searchType, final List<Lesson> list) {
        final List<List<Object>> base = new ArrayList<>();
        if (searchType == TYPE1) {
            int check = 0;
            int check2 = 0;
            for (int i = 0; i < Day.values().length * Controller.getController().getClassrooms().size() + Day.values().length; i++) {
                 base.add(new ArrayList<>());
                 if (i == (Day.values().length * Controller.getController().getClassrooms().size() + Day.values().length) / Day.values().length * check) {
                     base.get(i).add(Day.values()[check].getDay());
                     for (int y = 1; y <= Hour.values().length; y++) {
                         base.get(i).add(Hour.values()[y - 1].getHour());
                     }
                     check++;
                 } else {
                     base.get(i).add(Controller.getController().getClassrooms().get(check2));
                     for (int y = 1; y < EMPTY; y++) {
                         base.get(i).add("");
                     }
                     if (check2 == Controller.getController().getClassrooms().size() - 1) {
                         check2 = 0;
                     } else {
                         check2++;
                     }
                 }
            }
            
            if (list == null) {
                return base;
            }
            list.forEach(x -> System.out.println(x.getSubject().getName())); //la lista che arriva � vuota
            for (final Lesson lesson : list) {
                final String day = lesson.getDay().getDay();
                final String hour = lesson.getHour().getHour();
                final String classRoom = lesson.getClassRoom();
                int dayVal = 0;
                int hourVal = 0;
                int classVal = 0;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getDay())) {
                        dayVal = i + 1;
                    }
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getHour())) {
                        hourVal = i + 1;
                    }
                }
                for (final String classValue : Controller.getController().getClassrooms()) {
                    if (classValue.equals(classRoom)) {
                        classVal = Controller.getController().getClassrooms().indexOf(classValue) + 1;
                    }
                }
                base.get(classVal * dayVal + 1).set(hourVal, lesson);
            }
            
            
            
            return base;
        }
        
        if (searchType == TYPE2) {
            for (int i = 0; i < Day.values().length + 1; i++) {
                base.add(new ArrayList<>());
                if (i == 0) {
                    base.get(i).add("");
                    for (int y = 1; y <= Hour.values().length; y++) {
                        base.get(i).add(Hour.values()[y - 1].getHour());
                    }
                } else {
                    base.get(i).add(Day.values()[i - 1].getDay());
                    for (int y = 1; y < EMPTY; y++) {
                        base.get(i).add("");
                    }
                }
            }
            
            if (list == null) {
                return base;
            }
            
            for (final Lesson lesson : list) {
                final String day = lesson.getDay().getDay();
                final String hour = lesson.getHour().getHour();
                int dayVal = 0;
                int hourVal = 0;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getDay())) {
                        dayVal = i + 1;
                    }
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getHour())) {
                        hourVal = i + 1;
                    }
                }
                base.get(dayVal + 1).set(hourVal, lesson);
            }
            
            
            
            return base;
        }
        return null;
    }

}