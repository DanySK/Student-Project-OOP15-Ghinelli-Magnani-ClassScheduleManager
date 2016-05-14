package controller;

import java.io.IOException;

import model.SchedulesModel;

public interface IDataManager {
    
    void readConfig(final SchedulesModel model) throws IOException;
    public void saveFile(final String fileName, final SchedulesModel model) throws IOException;
    public void openFile(final String fileName, SchedulesModel model) throws IOException, ClassNotFoundException;
}
