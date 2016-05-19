package view.utility;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Controller;
import controller.utility.Pair;

public final class ColorUtility {
    
    private static List<Pair<String, Color>> colors;

    private ColorUtility() {
        
    }
    
    public static List<Pair<String, Color>> getColorsByYear() {
        if (colors != null) {
            return colors;
        }
        final List<Pair<String, Color>> colorsByYear = new ArrayList<>();
        Controller.getController().getYears().forEach(x -> {
            colorsByYear.add(new Pair<>(x, Color.BLUE)); // da sistemare
        });
        colors = colorsByYear;
        return colorsByYear;
    }
}
