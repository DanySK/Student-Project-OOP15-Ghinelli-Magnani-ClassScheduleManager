package controller;

import java.util.List;

import model.Court;
import model.Day;
import model.Hour;
import model.Semester;
import model.Year;
import model_interface.ISchedulesModel;
import model_interface.ITeaching;

/**
 * 
 * Class of the interface IControllerViewManagerImpl.
 *
 */

public class ControllerViewManagerImpl implements IControllerViewManager {
    
    private static final int SEMESTERINDEX = 6;
    private static final int CLASSROOMINDEX = 5;

    @Override
    public void addCourse(final List<String> values, final ISchedulesModel model) {
        Court court = null;
        Year year = null;
        for (int i = 0; i < Court.values().length; i++) {
            if (Court.values()[i].getDef().equals(values.get(2))) {
                court = Court.values()[i];
            }
        }
        for (int i = 0; i < Year.values().length; i++) {
            if (Year.values()[i].getYear().equals(values.get(1))) {
                year = Year.values()[i];
            }
        }
        try {
            model.addTeaching(values.get(0), year, court);
        } catch (IllegalArgumentException e) {
            Controller.getController().errorMessage(e.getMessage());
        }
    }

    @Override
    public void addLesson(final List<String> values, final ISchedulesModel model) {
        final ITeaching teaching = model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(0))).findFirst().get();
        Semester semester;
        Day day = null;
        Hour hour = null;
        final Integer duration = Integer.valueOf(values.get(2));
        if (values.get(SEMESTERINDEX).equals("1")) {
            semester = Semester.values()[0];
        } else {
            semester = Semester.values()[1];
        }
        for (int i = 0; i < Day.values().length; i++) {
            if (values.get(4).equals(Day.values()[i].getDay())) {
                day = Day.values()[i];
            }
        }
        for (int i = 0; i < Hour.values().length; i++) {
            if (values.get(3).equals(Hour.values()[i].getHour())) {
                if (duration > Hour.values().length - i) {
                    Controller.getController().errorMessage("Limit of hours surpassed!");
                }
                int check;
                for (check = 0; check < duration; check++) {
                    hour = Hour.values()[i + check];
                    try {
                        model.addLesson(values.get(1), teaching, semester, values.get(CLASSROOMINDEX), hour, day, 1);
                    } catch (Exception e) {
                        Controller.getController().errorMessage(e.getMessage());
                    }
                }
            }
        }
    }

}
