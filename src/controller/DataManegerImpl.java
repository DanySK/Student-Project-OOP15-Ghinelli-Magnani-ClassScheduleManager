package controller;

import model.SchedulesModel;
import model.interfaces.ISchedulesModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

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

public class DataManegerImpl implements IDataManager {
    
  private final Path configPath = FileSystems.getDefault().getPath("res", "config.yml");

  @Override
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
  
  @Override
  public void saveFile(final String fileName, final ISchedulesModel model) throws IOException {
    final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
    oos.writeObject(model);
    oos.close();
  }
  
  @Override
  public SchedulesModel openFile(final String fileName) throws IOException, ClassNotFoundException {
    try {
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
    try (final FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") 
                                                           + File.separator + "Lessons.xls"))) {
      workbook.write(out);
      workbook.close();
      Controller.getController().errorMessage("Successful export!");
    } catch (FileNotFoundException e) {
      Controller.getController().errorMessage("File not found!");
    } catch (IOException e) {
      Controller.getController().errorMessage("IO errors!");
    }
  }

}
