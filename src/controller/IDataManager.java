package controller;

import java.io.IOException;

import model.SchedulesModel;

public interface IDataManager {
    
    void readConfig(final SchedulesModel model) throws IOException;
    
    void saveFile(final String fileName, final SchedulesModel model) throws IOException;
    
    SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException;
}
