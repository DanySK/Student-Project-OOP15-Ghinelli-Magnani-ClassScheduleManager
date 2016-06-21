package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import controller.utility.Pair;
import model.Court;
import model.SchedulesModel;
import model.Semester;
import model.Year;
import model_interface.ILesson;
import model_interface.ISchedulesModel;
import view.IView;

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    private ISchedulesModel model = new SchedulesModel();
    private Optional<IView> view = Optional.empty();
    private final IDataManager data = new DataManegerImpl();
    private final IControllerViewManager manager = new ControllerViewManagerImpl();
    private Semester sem = Semester.FIRST_SEMESTER;
    private String searchType = "Total";
    private String searchValue = "Total";

    private Controller() {
        this.readConfiguration();
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
    
    public void readConfiguration() {
        try {
            data.readConfig(this.model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    public void saveData(final File file) {
        try {
            data.saveFile(file.getPath(), model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    public void loadData(final File file) {
        
        try {
            this.model = data.openFile(file.getPath());
        } catch (ClassNotFoundException e) {
            this.errorMessage(e.getMessage());
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(this.searchType, this.searchValue);
    }
    
    public void excelExport(final HSSFWorkbook workbook) {
        data.exportInExcel(workbook);
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
        return manager.getLessonsValues(model);
    }
    
    public Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        return manager.getCoursesValues();
    }
    
    public Map<String, List<String>> getSearchValues() {
        return manager.getSearchValues(model);
    }

    public void addCourse(final List<String> values) {
        manager.addCourse(values, model);
    }
    
    public void addLesson(final List<String> values) {
        manager.addLesson(values, model);
        this.searchBy(this.searchType, this.searchValue);
    }
    
    public void searchBy(final String typeValue, final String valueM) {
        this.searchType = typeValue;
        this.searchValue = valueM;
        if ("By Year".equals(typeValue)) {
            Year year = null;
            for (int i = 0; i < Year.values().length; i++) {
                if (Year.values()[i].getYear().equals(valueM)) {
                    year = Year.values()[i];
                }
            }
            this.view.get().addData(0, this.model.getLessons(null, null, year, null, this.sem, null, null, null));
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
    
    public void deleteLesson(final ILesson lesson) {
        try {
            this.model.deleteLesson(lesson);
        } catch (IllegalArgumentException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(this.searchType, this.searchValue);
    }
    
    public void setChangements(final List<ILesson> list) {
        try {
            this.model.checkChanges(list);
        } catch (IllegalArgumentException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(searchType, searchValue);
    }
    
    public Map<String, List<String>> deleteProfessorValues() {
        return this.manager.getProfessorValues();
    }
    
    public Map<String, List<String>> deleteTeachingValues() {
        return this.manager.getTeachingValues(model);
    }
    
    public void editMode(final boolean set) {
        this.view.get().editMode(set);
    }
    
    /*public void deleteProfessor(final String prof) {
        this.model.deleteProfessor(prof);
    }
    
    public void deleteTeaching(final String teaching) {
        this.model.deleteTeaching(teaching);
    }*/
}
