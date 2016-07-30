/**
 * 
 */
package model;

/**
 * @author Martina Magnani
 *
 */
public enum Court {
    /**
     * The teachings that are used by both the courses
     */
    COMUNE("Corte comune ad entrambi i curricula"),
    /**
     * The teachings of the University of Computer Engineering in Cesena
     */
    INGEGNERIA("Corte d'Ingegneria"),
    /**
     * The teachings of the University of Computer Science in Cesena
     */
    SCIENZE("Corte di Scienze");
    
    private final String def;
    
    private Court(final String def) {
        this.def = def;
    }
    /**
     * 
     * @return
     *          definition of court
     */
    public String getDef() {
        return this.def;
    }
}
