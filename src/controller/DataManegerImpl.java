package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import model.SchedulesModel;
import model_interface.ISchedulesModel;

public class DataManegerImpl implements IDataManager {
    
    private final Path configPath = FileSystems.getDefault().getPath("res", "config.yml");

    public void readConfig(final ISchedulesModel model) throws IOException {
        try {
            final List<String> contentFile = Files.readAllLines(this.configPath);
    
            for (final String s : contentFile) {
                final StringTokenizer st = new StringTokenizer(s, ":");
                if (st.countTokens() == 2) {
                    final String temp = st.nextToken();
                    if ("aula".equals(temp)) {
                        model.addClassroom(st.nextToken());
                    }
                    /*if ("anno".equals(temp)) {
                        model.addYears(st.nextToken());
                    }
                    if ("prof".equals(temp)) {
                        model.addProfessor(st.nextToken());
                    }*/
                }
            }
        } catch (IOException e) {
            throw new IOException("Illegal configuration file format");
        }
    }
    /**
     * Method to save an object on a file.
     * 
     * @param fileName The system-dependent filename.
     * @param obj Object to be saved on file.
     * @throws IOException {@link ObjectOutputStream#writeObject(Object)}.
     * @author Martina Magnani
     */
    public void saveFile(final String fileName, final ISchedulesModel model) throws IOException {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(model);
            oos.close();
    }
    /**
     * Method to load an object on a file.
     * 
     * @param fileName The system-dependent filename.
     * @return Object loaded from a file.
     * @throws IOException construct method of {@link ObjectIntputStream}.
     * @throws ClassNotFoundException {@link ObjectInputStream#readObject()}.
     * @author Martina Magnani
     */
    public SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException {
        try{
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            final SchedulesModel model = (SchedulesModel) ois.readObject();
            ois.close();
            return model;
        } catch (IOException e) {
            throw new IOException("Illegal file format");
        }
    }
    @Override
    public void exportInExcel(final HSSFWorkbook workbook) {
        try (final FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + File.separator + "Lessons.xls"))) {
            workbook.write(out);
            workbook.close();
            System.out.println("Export successful");
        } catch (FileNotFoundException e) {
            Controller.getController().errorMessage("File not found!");
        } catch (IOException e) {
           Controller.getController().errorMessage("IO errors!");
        }
    }

}
