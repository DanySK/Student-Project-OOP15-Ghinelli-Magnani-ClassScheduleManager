/**
 * 
 */
package model;

/**
 * Enumerator class used to represent available classroom inside the faculty of engineering and computer sciences in Cesena
 * @author Marti
 *
 */
public enum ClassRoom {
    /**
     * Aula Magna
     */
    AULA_MAGANA("Aula Magna"),
    /**
     * Aula A
     */
    AULA_A("Aula A"),
    /**
     * Aula B
     */
    AULA_B("Aula B"),
    /**
     * Aula C
     */
    AULA_C("Aula C"),
    /**
     * Aula D
     */
    AULA_D("Aula D"),
    /**
     * Aula E
     */
    AULA_E("Aula E"),
    /**
     * Aula in via Rasi
     */
    AULA_VIA_RASI("Aula in Via Rasi"),
    /**
     * Laboratorio Vela
     */
    LAB_VELA("Laboratorio Vela"),
    /**
     * Laboratorio 2
     */
    LAB_2("Laboratorio 2"),
    /**
     * Laboratorio 3
     */
    LAB_3("Laboratorio 3");
    
    private final String name;
    
    private ClassRoom(final String s) {
            name = s;
    }
    
    /**
     * 
     * @return 
     *          the complete name of the classroom.
     */
    public String getName() {
            return name;
    }
}
