package view.utility;

import java.awt.Color; 
import java.util.ArrayList;
import java.util.List;

import controller.utility.Pair;
import model.Year;

public final class ColorUtility {
    
    private static List<Pair<String, Color>> colors;

    private ColorUtility() {
        
    }
    
    public static List<Pair<String, Color>> getColorsByYear() {
        if (colors != null) {
            return colors;
        }
        final List<Pair<String, Color>> colorsByYear = new ArrayList<>();
        colorsByYear.add(new Pair<>(Year.PRIMO_MAGISTRALE.getYear(), Color.BLUE.brighter()));
        colorsByYear.add(new Pair<>(Year.PRIMO_TRIENNALE.getYear(), Color.CYAN));
        colorsByYear.add(new Pair<>(Year.SECONDO_ING_TRIENNALE.getYear(), Color.GRAY));
        colorsByYear.add(new Pair<>(Year.SECONDO_OPZ_MAGISTRALE.getYear(), Color.GREEN));
        colorsByYear.add(new Pair<>(Year.SECONDO_SCI_TRIENNALE.getYear(), Color.LIGHT_GRAY));
        colorsByYear.add(new Pair<>(Year.SECONDO_TRIENNALE.getYear(), Color.MAGENTA));
        colorsByYear.add(new Pair<>(Year.TERZO_ING_TRIENNALE.getYear(), Color.ORANGE));
        colorsByYear.add(new Pair<>(Year.TERZO_OPZ_TRIENNALE.getYear(), Color.PINK));
        colorsByYear.add(new Pair<>(Year.TERZO_SCI_TRIENNALE.getYear(), Color.RED.brighter()));
        colorsByYear.add(new Pair<>(Year.TERZO_TRIENNALE.getYear(), Color.YELLOW));
        colors = colorsByYear;
        return colorsByYear;
    }
}
