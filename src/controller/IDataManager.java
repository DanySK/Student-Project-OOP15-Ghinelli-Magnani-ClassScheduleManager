package controller;

import java.io.IOException;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.SchedulesModel;
import model_interface.ISchedulesModel;

public interface IDataManager {
    
    void readConfig(final ISchedulesModel model) throws IOException;
    
    void saveFile(final String fileName, final ISchedulesModel model) throws IOException;
    
    SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException;
    
    /**
     * Method which takes a HSSFWorkbook object and saves it in the home directory of the user.
     * @param workbook The workbook containing the table.
     */
    
    void exportInExcel(final HSSFWorkbook workbook);
}
