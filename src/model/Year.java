/**
 * 
 */
package model;

/**
 * Enumerator class that represents all the different types concerning courses.
 * 
 * @author Martina Magnani
 *
 */
public enum Year {
    /**
     * Exams of the first year of the bachelor’s degree.
     */
    PRIMO_ANNO("1° anno laurea triennale"),
    /**
     * Exams of the second year of the bachelor’s degree.
     */
    SECONDO_ANNO("2° anno laurea triennale"),
    /**
     * Exams of the second year of engineering bachelor degree
     */
    SECONDO_ANNO_PROFILO_A("2° anno laurea triennale - INGEGNERIA"),
    /**
     * Exams of the second year of science bachelor degree
     */
    SECONDO_ANNO_PROFILO_B("2° anno laurea triennale - SCIENZE"),
    /**
     * Exams of the third year of the bachelor’s degree.
     */
    TERZO_ANNO("3° anno laurea triennale"),
    /**
     * Exams of the third year of engineering bachelor degree
     */
    TERZO_ANNO_PROFILO_A("3° anno laurea triennale - INGEGNERIA"),
    /**
     * Exams of the third year of science bachelor degree
     */
    TERZO_ANNO_PROFILO_B("3° anno laurea triennale - SCIENZE"),
    /**
     * Optional exams of the bachelor’s degree.
     */
    TERZO_ANNO_OPZIONALI("3° anno laurea triennale - OPZIONALI"),
    /**
     * Exams of the first year of the master’s degree.
     */
    PRIMO_ANNO_MAGISTRALE("1° anno laurea magistrale"),
    /**
     * Optional exams of the bachelor’s degree.
     */
    SECONDO_ANNO_MAGISTRALE_OPZIONALI("2° anno laurea magistrale - OPZIONALI");
    
    private final String descr;
    
    private Year(final String s) {
        this.descr = s;
    }
    /**
     * 
     * @return 
     *          complete description of the type.
     */
    public String getDescription() {
        return descr;
    }
}
