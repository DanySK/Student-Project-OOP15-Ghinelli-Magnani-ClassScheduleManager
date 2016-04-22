package view.utility;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Hour;

public class StructCreatorImpl implements IStructCreator {
    
    private List<List<Object>> base;
    private static final int TYPE1 = 0;
    private static final int TYPE2 = 1;

    @Override
    public List<List<Object>> getStruct(final int searchType, final List<Object> list) { // creare nuovo metodo apposta per creare le tabelle vuote da riempire in seguito, oppure lasciare tutto insieme, da chiedere al prof, ricordasi che sto chiedendo informazioni direttamente alle enum del model
        if (searchType == TYPE1) { // vista totale usabile in più ricerche
            this.base = new ArrayList<>();
            int check = 0;
            for (int i = 0; i < Day.values().length * 3/*(lunghezza lista aule)*/ + Day.values().length; i++) {
                 this.base.add(new ArrayList<>());
                 if (i == (Day.values().length * 3/*(lunghezza lista aule)*/ + Day.values().length) / Day.values().length * check) {
                     this.base.get(i).add(Day.getName(check));
                     for (int y = 1; y <= Hour.values().length; y++) {
                         this.base.get(i).add(Hour.getHour(y - 1));
                     }
                     check++;
                 } else {
                     this.base.get(i).add("Aula prova"); // dalla lista delle classi prendere con indice i - 1
                     for (int y = 1; y < 9; y++) {
                         this.base.get(i).add("");
                     }
                 }
            }
            
            
            
            /*for (Object a (lista di lezioni, non di object): list) {
                // String day = a.getDay();
                // String hour = a.getHour();
                // String class = a.getClass();
                int dayVal;
                int hourVal;
                int classVal;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getName())) {
                        dayVal = i + 1;
                    }
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getName())) {
                        hourVal = i + 1;
                    }
                }
                for (class a : classList) {
                    if (a.equals(class)) {
                        classVal = classList.indexOf(a) + 1;
                    }
                }
                this.base.get(classVal * dayVal + 1).set(hourVal, a);
            }*/
            
            
            
            return this.base;
        }
        
        if (searchType == TYPE2) {
            this.base = new ArrayList<>();
            for (int i = 0; i < Day.values().length + 1; i++) {
                this.base.add(new ArrayList<>());
                if (i == 0) {
                    this.base.get(i).add("");
                    for (int y = 1; y <= Hour.values().length; y++) {
                        this.base.get(i).add(Hour.getHour(y - 1));
                    }
                } else {
                    this.base.get(i).add(Day.getName(i - 1));
                    for (int y = 1; y < 9; y++) {
                        this.base.get(i).add("");
                    }
                }
            }
            
            
            
            /*for (Object a (lista di lezioni, non di object): list) {
                // String day = a.getDay();
                // String hour = a.getHour();
                // String class = a.getClass();
                int dayVal;
                int hourVal;
                int classVal;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getName())) {
                        dayVal = i + 1;
                    }
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getName())) {
                        hourVal = i + 1;
                    }
                }
                this.base.get(dayVal + 1).set(hourVal, a);
            }*/
            
            
            
            return this.base;
        }
        return null;
    }

}
