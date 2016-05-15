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
import model.Day;
import model.Hour;
import model.SchedulesModel;

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    private final SchedulesModel model = new SchedulesModel();

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
        returnValue.put(new Pair<>("Day", false), days);
        returnValue.put(new Pair<>("Class", false), this.model.getClassroomsList());
        returnValue.put(new Pair<>("Hour", false), hours);
        returnValue.put(new Pair<>("Duration", false), Arrays.asList("1", "2", "3", "4", "5"));
        return returnValue;
    }
    
    public Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        returnValue.put(new Pair<>("Course", true), this.getCourseName());
        returnValue.put(new Pair<>("Year", false), this.getYears());
        returnValue.put(new Pair<>("Semester", false), Arrays.asList("1", "2"));
        return returnValue;
    }
    
    public Map<String, List<String>> getSearchValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        returnValue.put("By Year", this.getYears());
        returnValue.put("By Prof.", this.getProfessors());
        returnValue.put("By Course", this.getCourseName());
        returnValue.put("By Classroom", this.getClassrooms());
        return returnValue;
    }

    public void addCourse(final List<String> values) {
        
    }
    
    public void addLesson(final List<String> values) {
        
    }
    
    public void searchBy(final String type, final String value) {
        
    }
    
    public void setSemester(final int semester) {
        
    }
    
}
