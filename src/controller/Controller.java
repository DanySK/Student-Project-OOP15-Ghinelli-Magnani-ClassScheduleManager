package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import controller.utility.Pair;
import model.Court;
import model.SchedulesModel;
import model.Semester;
import model.Year;
import model_interface.ILesson;
import model_interface.ISchedulesModel;
import view.IView;

/**
 * 
 * Class which interacts between the view and the model, it follows the singleton pattern.
 *
 */

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
    
    /**
     * Method which return the instance of the controller, and if the controller doesn't exist yet, it creates a new one.
     * @return The controller.
     */

    public static Controller getController() {
        synchronized (Controller.class) {
            if (!singleton.isPresent()) {
                singleton = Optional.of(new Controller());
            }
        }
        return singleton.get();
    }
    
    /**
     * Method used to assign the view to the controller.
     * @param viewValue An instance of the view.
     */
    
    public void setView(final IView viewValue) {
        this.view = Optional.of(viewValue);
    }
    
    /**
     * Method which read the initial configuration for the program.
     */
    
    public void readConfiguration() {
        try {
            data.readConfig(this.model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    /**
     * Method which saves the data of the table in a file.
     * @param file The file where the data will be saved.
     */
    
    public void saveData(final File file) {
        try {
            data.saveFile(file.getPath(), model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    /**
     * Method which loads the data of the table in a file.
     * @param file The file where the data will be loaded.
     */
    
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
    
    /**
     * Method which exports in a excel file the table.
     * @param workbook The workbook containing the file.
     */
    
    public void excelExport(final HSSFWorkbook workbook) {
        data.exportInExcel(workbook);
    }
    
    /**
     * Method which gives a list of the name of courses.
     * @return The list.
     */

    public List<String> getCourseName() {
        return model.getTeachingsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    /**
     * Method which gives a list of the name of professors.
     * @return The list.
     */
    
    public List<String> getProfessors() {
        return model.getProfessorsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    /**
     * Method which gives a list of the name of classrooms.
     * @return The list.
     */
    
    public List<String> getClassrooms() {
        return model.getClassroomsList();
    }
    
    /**
     * Method which provides to the view the lessons of the model for the add dialog of JComboBoxs.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */
    
    public Map<Pair<String, Boolean>, List<String>> getLessonsValues() {
        return manager.getLessonsValues(model);
    }
    
    /**
     * Method which provides to the view the courses of the model for the add dialog of JComboBoxs.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */
    
    public Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        return manager.getCoursesValues();
    }
    
    /**
     * Method which provides to the view the search types and elements to update them dynamically.
     * @return The map containing the string of the search types and his list of the elements.
     */
    
    public Map<String, List<String>> getSearchValues() {
        return manager.getSearchValues(model);
    }
    
    /**
     * Method which add to the model a course providing all the information needed.
     * @param values The list of informations.
     */

    public void addCourse(final List<String> values) {
        manager.addCourse(values, model);
    }
    
    /**
     * Method which add to the model a lesson providing all the information needed.
     * @param values The list of informations.
     */
    
    public void addLesson(final List<String> values) {
        manager.addLesson(values, model);
        this.searchBy(this.searchType, this.searchValue);
    }
    
    /**
     * Method which change the table's type of view.
     * @param typeValue The type of view.
     * @param valueM The element of the type.
     */
    
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
    
    /**
     * Method which change the semester in the view's table.
     * @param semester The semester which will be seen.
     */
    
    public void setSemester(final int semester) {
        if (semester == 1) {
            this.sem = Semester.FIRST_SEMESTER;
            this.searchBy(this.searchType, this.searchValue);
        } else {
            this.sem = Semester.SECOND_SEMESTER;
            this.searchBy(this.searchType, this.searchValue);
        }
    }
    
    /**
     * Method which pass the view a message of a certain error.
     * @param error The message.
     */
    
    public void errorMessage(final String error) {
        this.view.get().errorDialog(error);
    }
    
    /**
     * Method used to confirm the changements in the view's table.
     * @param list The list of ILessons suitably changed.
     */
    
    public void setChangements(final List<ILesson> list) {
        try {
            this.model.checkChanges(list, this.sem);
        } catch (IllegalArgumentException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(searchType, searchValue);
    }
    
    /**
     * Method which provides to the view the professors of the model for the delete dialog of a single JComboBox.
     * @return The map containing the string as the name of the information and the list of professors.
     */
    
    public Map<String, List<String>> deleteProfessorValues() {
        return this.manager.getProfessorValues();
    }
    
    /**
     * Method which provides to the view the teachings(courses) of the model for the delete dialog of a single JComboBox.
     * @return The map containing the string as the name of the information and the list of teachings.
     */
    
    public Map<String, List<String>> deleteTeachingValues() {
        return this.manager.getTeachingValues(model);
    }
    
    /**
     * Method used to tell the view to change in edit mode or not.
     * @param set Says if the view will change in edit mode or return in normal mode.
     */
    
    public void editMode(final boolean set) {
        this.view.get().editMode(set);
    }
    
    public void deleteProfessor(final String prof) {
        this.model.deleteProfessor(prof);
    }
    
    public void deleteTeaching(final String teaching) {
        this.model.deleteTeaching(teaching);
    }
}
