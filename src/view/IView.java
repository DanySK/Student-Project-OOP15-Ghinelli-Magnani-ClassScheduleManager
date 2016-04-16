package view;

import java.util.List;

public interface IView {
    
    void addData(List<List<Object>> list);
    
    void clearData();
    
    void refreshSearchList();

}
