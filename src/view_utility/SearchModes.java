package view_utility;

public enum SearchModes {
    
    SEMESTRE("Semestre"),
    ANNO("Anno accademico"),
    MATERIA("Materia"),
    PROF("Prof."),
    AULA("Aula"),
    GIORNO("Giorno");
    
    private String name;

    SearchModes(final String name) {
        this.name = name;
    }
    
    public static String getName(final int i) {
        return SearchModes.values()[i].name;
    }

}
