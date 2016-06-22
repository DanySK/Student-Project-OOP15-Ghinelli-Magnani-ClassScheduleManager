package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.utility.Pair;
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
    public Map<Pair<String, Boolean>, List<String>> getLessonsValues(final ISchedulesModel model) {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> days = new ArrayList<>();
        final List<String> hours = new ArrayList<>();
        for (int  i = 0; i < Day.values().length; i++) {
            days.add(Day.values()[i].getDay());
        }
        for (int  i = 0; i < Hour.values().length; i++) {
            hours.add(Hour.values()[i].getHour());
        }
        returnValue.put(new Pair<>("Name", false), Controller.getController().getCourseName());
        returnValue.put(new Pair<>("Prof.", true), Controller.getController().getProfessors());
        returnValue.put(new Pair<>("Duration", false), Arrays.asList("1", "2", "3", "4", "5"));
        returnValue.put(new Pair<>("Hour", false), hours);
        returnValue.put(new Pair<>("Day", false), days);
        returnValue.put(new Pair<>("Class", false), model.getClassroomsList());
        returnValue.put(new Pair<>("Semester", false), Arrays.asList("1", "2"));
        return returnValue;
    }

    @Override
    public Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        for (int i = 0; i < Year.values().length; i++) {
            years.add(Year.values()[i].getYear());
        }
        returnValue.put(new Pair<>("Teaching", true), Controller.getController().getCourseName());
        returnValue.put(new Pair<>("Year", false), years);
        returnValue.put(new Pair<>("Court", false), courts);
        return returnValue;
    }

    @Override
    public Map<String, List<String>> getSearchValues(final ISchedulesModel model) {
        final Map<String, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        for (int i = 0; i < Year.values().length; i++) {
            years.add(Year.values()[i].getYear());
        }
        returnValue.put("By Year", years);
        returnValue.put("By Court", courts);
        returnValue.put("By Prof.", model.getProfessorsActive().stream().map(x -> x.getName()).collect(Collectors.toList()));
        returnValue.put("By Teaching", model.getTeachingActive().stream().map(x -> x.getName()).collect(Collectors.toList()));
        returnValue.put("By Classroom", Controller.getController().getClassrooms());
        return returnValue;
    }
   
    @Override
    public Map<String, List<String>> getProfessorValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        returnValue.put("Select the professor to delete", Controller.getController().getProfessors());
        return returnValue;
    }

    @Override
    public Map<String, List<String>> getTeachingValues(final ISchedulesModel model) {
        final Map<String, List<String>> returnValue = new HashMap<>();
        returnValue.put("Select the teaching to delete", model.getTeachingsList().stream().map(x -> x.getName()).collect(Collectors.toList()));
        return returnValue;
    }

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
