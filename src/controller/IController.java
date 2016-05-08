package controller;

import java.util.List;

import view.IView;

public interface IController {
    
    void setView(IView view);
    
    void setViewData(List<Object> list);

}
