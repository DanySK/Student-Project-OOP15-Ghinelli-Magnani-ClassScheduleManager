package view;


import java.util.List;

public interface IView {
    
    void addData(List<Object> list);
    
    void clearData();
    
    void refreshSearchList();

}
