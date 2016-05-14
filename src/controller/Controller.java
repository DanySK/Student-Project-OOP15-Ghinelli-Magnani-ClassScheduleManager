package controller;

import java.io.IOException; 
import java.util.ArrayList;  
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<String> getYears() {
        //model.getyearslist
        return new ArrayList<>(Arrays.asList("primo", "secondo", "terzo"));
    }

    public List<String> getCourseName() {
      //model.getcoursenamelist
        return new ArrayList<>(Arrays.asList("OOP", "BASI", "ARCH"));
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
        //da ordinare le liste ottenute
        returnValue.put(new Pair<>("Name", false), this.getCourseName());
        returnValue.put(new Pair<>("Prof.", true), Arrays.asList("Viroli", "Ghini"));
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

}
