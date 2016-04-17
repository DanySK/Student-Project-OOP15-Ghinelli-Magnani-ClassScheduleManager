package view_utility;

public enum SearchModes {
    // sole e uniche ricerche da fare
    ANNO("Anno accademico"), //1 anno, 2 e 3 anno separati dall'indirizzo, poi solo 3 opzionali
    MATERIA("Materia"),
    PROF("Prof."),
    AULA("Aula");
    
    private String name;

    SearchModes(final String n) {
        this.name = n;
    }
    
    public static String getName(final int i) {
        return SearchModes.values()[i].name;
    }

}
