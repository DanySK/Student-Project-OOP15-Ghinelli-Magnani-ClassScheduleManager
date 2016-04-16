package view_utility;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Hour;

public class StructCreatorImpl implements IStructCreator {
    
    private List<List<Object>> base;

    @Override
    public List<List<Object>> getStruct(final int searchType, final List<Object> list) { // creare nuovo metodo apposta per creare le tabelle vuote da riempire in seguito
        if (searchType == 0) { // da modificare in totale primo semestre in seguito
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
                     this.base.get(i).add("Aula prova");
                     for (int y = 1; y < 9; y++) {
                         this.base.get(i).add("");
                     }
                 }
             }
             return this.base;
        }
        if (searchType == 1) {
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
            return this.base;
        }
        return null;
    }

}
