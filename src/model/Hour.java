/**
 * 
 */
package model;

/**

 * The enum Hour has the task of model college schedules 
 * @author Martina Magnani
 * */
public enum Hour {
    NOVE_DIECI("9/10"),
    DIECI_UNDICI("10/11"),
    UNDICI_DODICI("11/12"),
    DODICI_TREDICI("12/13"),
    //TREDICI_QUATTORDICI("13/14"), questo orario non c'è mai, pausa pranzo
    QUATTORDICI_QUINDICI("14/15"),
    QUINDICI_SEDICI("15/16"),
    SEDICI_DICIASSETTE("16/17"),
    DICIASSETE_DICIOTTO("17/18");
    
    private final String hour;
    /**
     * Constructor of the enum Hour
     * @param hour
     *          the hour of the lesson
     */
    private Hour(final String hour) {
        this.hour = hour;
    }
    /**
     * Method that return the required hour
     * @param i
     * @return
     *         required hour 
     */
    public static String getHour(final int i) {
        return Hour.values()[i].hour;
    }
}
