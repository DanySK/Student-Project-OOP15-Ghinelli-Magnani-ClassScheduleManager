package view;

import java.util.List; 

import model_interface.ILesson;

public interface IView {
    
    void addData(final int type, List<ILesson> list);
    
    void editMode(final boolean set);
    
    void refreshSearchList();
    
    void errorDialog(final String message);
    
    void exportData();

}
