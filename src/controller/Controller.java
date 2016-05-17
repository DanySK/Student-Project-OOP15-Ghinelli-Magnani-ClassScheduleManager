package controller;

import java.io.File;
import java.io.IOException; 
import java.util.ArrayList;  
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import controller.utility.Pair;
import model.Court;
import model.Day;
import model.Hour;
import model.Lesson;
import model.SchedulesModel;
import model.Semester;
import model.Teaching;
import view.IView;

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    private final SchedulesModel model = new SchedulesModel();
    private Optional<IView> view = Optional.empty();

    private Controller() {
        
    }

    public static Controller getController() {
        synchronized (Controller.class) {
            if (!singleton.isPresent()) {
                singleton = Optional.of(new Controller());
            }
        }
        return singleton.get();
    }
    
    public void setView(final IView viewValue) {
        this.view = Optional.of(viewValue);
    }
    
    public void readConfiguration() throws IOException {
        final IDataManager data = new IDataManegerImpl();
        data.readConfig(this.model);
    }
    
    public void saveData(final File file) throws IOException {
        final IDataManager data = new IDataManegerImpl();
        data.saveFile(file.getPath(), model);
    }
    
    public void loadData(final File file) throws IOException {
        final IDataManager data = new IDataManegerImpl();
        try {
            data.openFile(file.getPath());
        } catch (ClassNotFoundException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error:", e);
        }
    }

    public List<String> getYears() {
        return model.getAcademicYearsList();
    }

    public List<String> getCourseName() {
        return model.getTeachingsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    public List<String> getProfessors() {
        return model.getProfessorsList().stream().sorted().collect(Collectors.toList());
    }
    
    public List<String> getClassrooms() {
        return model.getClassroomsList();
    }
    
    public Map<Pair<String, Boolean>, List<String>> getLessonsValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> days = new ArrayList<>();
        final List<String> hours = new ArrayList<>();
        for (int  i = 0; i < Day.values().length; i++) {
            days.add(Day.values()[i].getDay());
        }
        for (int  i = 0; i < Hour.values().length; i++) {
            hours.add(Hour.values()[i].getHour());
        }
        returnValue.put(new Pair<>("Name", false), this.getCourseName());
        returnValue.put(new Pair<>("Prof.", true), this.getProfessors());
        returnValue.put(new Pair<>("Duration", false), Arrays.asList("1", "2", "3", "4", "5"));
        returnValue.put(new Pair<>("Hour", false), hours);
        returnValue.put(new Pair<>("Day", false), days);
        returnValue.put(new Pair<>("Class", false), this.model.getClassroomsList());
        returnValue.put(new Pair<>("Semester", false), Arrays.asList("1", "2"));
        return returnValue;
    }
    
    public Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        returnValue.put(new Pair<>("Teaching", true), this.getCourseName());
        returnValue.put(new Pair<>("Year", false), this.getYears());
        returnValue.put(new Pair<>("Court", false), courts);
        return returnValue;
    }
    
    public Map<String, List<String>> getSearchValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        returnValue.put("By Year", this.getYears());
        returnValue.put("By Court", courts);
        returnValue.put("By Prof.", this.getProfessors());
        returnValue.put("By Teaching", this.getCourseName());
        returnValue.put("By Classroom", this.getClassrooms());
        return returnValue;
    }

    public void addCourse(final List<String> values) {
        for (int i = 0; i < Court.values().length; i++) {
            if (Court.values()[i].getDef().equals(values.get(2))) {
                this.model.addTeaching(values.get(0), values.get(1), Court.values()[i]);
            }
        }
    }
    
    public void addLesson(final List<String> values) { // schifo ma non si può fare altrimenti
        final Teaching teaching = this.model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(0))).findFirst().get();
        final Semester semester;
        Day day = null;
        Hour hour = null;
        final int duration = Integer.valueOf(values.get(2));
        if (values.get(6).equals("1")) {
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
                hour = Hour.values()[i];
            }
        }
        this.model.addLesson(values.get(1), teaching, semester, values.get(5), hour, day, duration);
    }
    
    public void searchBy(final String type, final String value) { //schifo ma non si può fare altrimenti
        if ("By Year".equals(type)) {
            this.view.get().addData(this.model.getLessons(null, null, value, null, null, null, null, null));
        }
        if ("By Court".equals(type)) {
            Court court = null;
            for (int i = 0; i < Court.values().length; i++) {
                if (Court.values()[i].getDef().equals(value)) {
                    court = Court.values()[i];
                }
            }
            this.view.get().addData(this.model.getLessons(null, null, null, court, null, null, null, null));
        }
    }
    
    public void setSemester(final int semester) {
        
    }
}
