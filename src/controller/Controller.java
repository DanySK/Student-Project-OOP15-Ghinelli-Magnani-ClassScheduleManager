package controller;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    
    private Controller() { }
    
    public static Controller getController() {
        synchronized (Controller.class) {
            if (!singleton.isPresent()) {
                singleton = Optional.of(new Controller());
            }
        }
        return singleton.get();
    }

    public List<String> getYears() {
        //model.getyearslist
        return new ArrayList<>(Arrays.asList("primo", "secondo", "terzo"));
    }

    public List<String> getCourseName() {
      //model.getcoursenamelist
        return new ArrayList<>(Arrays.asList("OOP", "BASI", "ARCH"));
    }

}
