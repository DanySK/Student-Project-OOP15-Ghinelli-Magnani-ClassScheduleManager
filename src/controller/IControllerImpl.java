package controller;

import java.util.List;

import view.IView;

public class IControllerImpl implements IController {
    
    private IView view;
    //ci vuole una classe model da cui partire
    
    public IControllerImpl(final IView viewValue) {
        this.view = viewValue;
    }

    @Override
    public void setView(final IView viewValue) {
        this.view = viewValue;
    }

    @Override
    public void setViewData(final List<Object> list) {
        this.view.addData(list); 
    }

}
