package controller;

import java.io.File; 
import java.io.IOException; 
import java.util.ArrayList;  
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import controller.utility.Pair;
import model.Court;
import model.Day;
import model.Hour;
import model.SchedulesModel;
import model.Semester;
import model.Teaching;
import model.Year;
import model_interface.ISchedulesModel;
import view.IView;

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    private ISchedulesModel model = new SchedulesModel();
    private Optional<IView> view = Optional.empty();
    private Semester sem = Semester.FIRST_SEMESTER;
    private String searchType = "Total";
    private String searchValue = "Total";

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
            this.model = data.openFile(file.getPath());
        } catch (ClassNotFoundException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error:", e);
        }
        this.searchBy(this.searchType, this.searchValue);
    }

    public List<String> getCourseName() {
        return model.getTeachingsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    public List<String> getProfessors() {
        return model.getProfessorsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
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
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        for (int i = 0; i < Year.values().length; i++) {
            years.add(Year.values()[i].getYear());
        }
        returnValue.put(new Pair<>("Teaching", true), this.getCourseName());
        returnValue.put(new Pair<>("Year", false), years);
        returnValue.put(new Pair<>("Court", false), courts);
        return returnValue;
    }
    
    public Map<String, List<String>> getSearchValues() {
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
        returnValue.put("By Classroom", this.getClassrooms());
        return returnValue;
    }

    public void addCourse(final List<String> values) {
        for (int i = 0; i < Court.values().length; i++) {
            if (Court.values()[i].getDef().equals(values.get(2))) {
                try {
                    this.model.addTeaching(values.get(0), values.get(1), Court.values()[i]);
                } catch (IllegalArgumentException e) {
                    this.errorMessage(e.getMessage());
                }
            }
        }
    }
    
    public void addLesson(final List<String> values) {
        final Teaching teaching = this.model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(0))).findFirst().get();
        final Semester semester;
        Day day = null;
        Hour hour = null;
        final Integer duration = Integer.valueOf(values.get(2));
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
                if (duration > Hour.values().length - i) {
                    this.errorMessage("Limit of hours surpassed!");
                }
                int check;
                for (check = 0; check < duration; check++) {
                    hour = Hour.values()[i + check];
                    try {
                        this.model.addLesson(values.get(1), teaching, semester, values.get(5), hour, day, 1);
                    } catch (Exception e) {
                        this.errorMessage(e.getMessage());
                    }
                }
            }
        }
        this.searchBy(this.searchType, this.searchValue);
    }
    
    public void searchBy(final String typeValue, final String valueM) {
        this.searchType = typeValue;
        this.searchValue = valueM;
        if ("By Year".equals(typeValue)) {
            this.view.get().addData(0, this.model.getLessons(null, null, valueM, null, this.sem, null, null, null));
        }
        if ("By Court".equals(typeValue)) {
            Court court = null;
            for (int i = 0; i < Court.values().length; i++) {
                if (Court.values()[i].getDef().equals(valueM)) {
                    court = Court.values()[i];
                }
            }
            this.view.get().addData(0, this.model.getLessons(null, null, null, court, this.sem, null, null, null));
        }
        if ("By Prof.".equals(typeValue)) {
            this.view.get().addData(0, this.model.getLessons(valueM, null, null, null, this.sem, null, null, null));
        }
        if ("By Teaching".equals(typeValue)) {
            this.view.get().addData(0, this.model.getLessons(null, valueM, null, null, this.sem, null, null, null));
        }
        if ("By Classroom".equals(typeValue)) {
            this.view.get().addData(1, this.model.getLessons(null, null, null, null, this.sem, valueM, null, null));
        }
        if ("Total".equals(typeValue)) {
            this.view.get().addData(0, this.model.getLessons(null, null, null, null, this.sem, null, null, null));
        }
        this.view.get().refreshSearchList();
    }
    
    public void setSemester(final int semester) {
        if (semester == 1) {
            this.sem = Semester.FIRST_SEMESTER;
            this.searchBy(this.searchType, this.searchValue);
        } else {
            this.sem = Semester.SECOND_SEMESTER;
            this.searchBy(this.searchType, this.searchValue);
        }
    }
    
    public void errorMessage(final String error) {
        this.view.get().errorDialog(error);
    }
}
