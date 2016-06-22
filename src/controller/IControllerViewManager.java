package controller;

import java.util.List;
import java.util.Map;

import controller.utility.Pair;
import model_interface.ISchedulesModel;

/**
 * 
 * Interface responsible for providing information in the right way for the view and 
 * adding the courses and lessons to the model.
 *
 */

public interface IControllerViewManager {
    
    /**
     * Method which provides to the view the lessons of the model for the add dialog of JComboBoxs.
     * @param model Model of the program.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */

    Map<Pair<String, Boolean>, List<String>> getLessonsValues(final ISchedulesModel model);
    
    /**
     * Method which provides to the view the courses of the model for the add dialog of JComboBoxs.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */
    
    Map<Pair<String, Boolean>, List<String>> getCoursesValues();
    
    /**
     * Method which provides to the view the search types and elements to update them dynamically.
     * @param model Model of the program.
     * @return The map containing the string of the search types and his list of the elements.
     */
    
    Map<String, List<String>> getSearchValues(final ISchedulesModel model);
    
    /**
     * Method which provides to the view the professors of the model for the delete dialog of a single JComboBox.
     * @return The map containing the string as the name of the information and the list of professors.
     */
    
    Map<String, List<String>> getProfessorValues();
    
    /**
     * Method which provides to the view the teachings(courses) of the model for the delete dialog of a single JComboBox.
     * @param model The model of the program.
     * @return The map containing the string as the name of the information and the list of teachings.
     */
    
    Map<String, List<String>> getTeachingValues(final ISchedulesModel model);
    
    /**
     * Method which add to the model a course providing all the information needed.
     * @param values The list of informations.
     * @param model The model of the program.
     */
    
    void addCourse(final List<String> values, final ISchedulesModel model);
    
    /**
     * Method which add to the model a lesson providing all the information needed.
     * @param values The list of informations.
     * @param model The model of the program.
     */
    
    void addLesson(final List<String> values, final ISchedulesModel model);
}
