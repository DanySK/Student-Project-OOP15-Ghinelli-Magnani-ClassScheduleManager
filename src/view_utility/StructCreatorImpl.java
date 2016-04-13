package view_utility;

import java.util.List;

public class StructCreatorImpl implements IStructCreator {

    @Override
    public Object[][] getStruct(final int searchType, final List<Object> list) {
        Object[][] retVal = new Object[20][10]; // dimensione in base al tipo di ricerca
        if (searchType == 0) { // da modificare in totale primo semestre in seguito
            int check = 0;
             for (int i = 0; i < 20/*mettere la lunghezza lista aule * lunghezza lista giorni + lunghezza lista giorni*/; i++) {
                 if (i == 5 * check) {
                     retVal[i][0] = "Lunedì";
                     for (int y = 1; y < 9 /*lughezza lista orari*/; y++) {
                         retVal[i][y] = "12";
                     }
                     check++;
                 } else {
                     retVal[i][0] = "Aula prova";
                     for (int y = 1; y < 9; y++) {
                         retVal[i][y] = "";
                     }
                 }
             }
             return retVal;
        }
        return null;
    }

}
