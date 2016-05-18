package view;

import java.util.List;

import model.Lesson;

public interface IView {
    
    void addData(final int type, List<Lesson> list);
    
    void editMode();
    
    void refreshSearchList();

}
