package controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

import model.SchedulesModel;

public class IDataManegerImpl implements IDataManager {
    
    private final Path configPath = FileSystems.getDefault().getPath("res", "config.yml");

    @Override
    public void readConfig(final SchedulesModel model) throws IOException {
        final List<String> contentFile = Files.readAllLines(this.configPath);
        for (final String s : contentFile) {
            final StringTokenizer st = new StringTokenizer(s, ":");
            if (st.countTokens() == 2) {
                final String temp = st.nextToken();
                if ("aula".equals(temp)) {
                    model.addClassroom(st.nextToken());
                }
            }
        }
    }

}
