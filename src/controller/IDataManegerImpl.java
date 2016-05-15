package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

import model.SchedulesModel;

public class IDataManegerImpl implements IDataManager {
    
    private final Path configPath = FileSystems.getDefault().getPath("res", "config.yml");

    public void readConfig(final SchedulesModel model) throws IOException {
        final List<String> contentFile = Files.readAllLines(this.configPath);
        for (final String s : contentFile) {
            final StringTokenizer st = new StringTokenizer(s, ":");
            if (st.countTokens() == 2) {
                final String temp = st.nextToken();
                if (temp.equals("aula")) {
                    model.addClassroom(st.nextToken());
                }
                if (temp.equals("anno")) {
                    model.addYears(st.nextToken());
                }
                if (temp.equals("prof")) {
                    model.addProfessor(st.nextToken());
                }
            }
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
    public void saveFile(final String fileName, final SchedulesModel model) throws IOException {
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
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            SchedulesModel model = (SchedulesModel) ois.readObject();
            ois.close();
            return model;
    }

}
