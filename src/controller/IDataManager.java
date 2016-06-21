package controller;

import java.io.IOException;

import javax.swing.JTable;

import model.SchedulesModel;
import model_interface.ISchedulesModel;

public interface IDataManager {
    
    void readConfig(final ISchedulesModel model) throws IOException;
    
    void saveFile(final String fileName, final ISchedulesModel model) throws IOException;
    
    SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException;
    
    void exportInExcel(final JTable table);
}
