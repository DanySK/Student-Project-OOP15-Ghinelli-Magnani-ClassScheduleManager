/**
 * 
 */
package model_interface;

import model.Court;
import model.Year;

/**
 * @author Martina Magnani
 *  
 */
public interface ITeaching extends java.io.Serializable{
    public String getName();
    public Year getYear();
    public Court getCourt();
}
