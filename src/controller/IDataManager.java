package controller;

import java.io.IOException;

import model.SchedulesModel;

public interface IDataManager {
    
    void readConfig(final SchedulesModel model) throws IOException;

}
