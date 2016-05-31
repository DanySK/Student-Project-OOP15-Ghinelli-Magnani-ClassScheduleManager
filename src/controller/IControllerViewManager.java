package controller;

import java.util.List;
import java.util.Map;

import controller.utility.Pair;
import model_interface.ISchedulesModel;

public interface IControllerViewManager {

    Map<Pair<String, Boolean>, List<String>> getLessonsValues(final ISchedulesModel model);
    
    Map<Pair<String, Boolean>, List<String>> getCoursesValues();
    
    Map<String, List<String>> getSearchValues(final ISchedulesModel model);
    
    void addCourse(final List<String> values, final ISchedulesModel model);
    
    void addLesson(final List<String> values, final ISchedulesModel model);
}
