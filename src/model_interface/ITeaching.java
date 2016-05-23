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
public interface ITeaching extends java.io.Serializable {
    String getName();
    String getYear();
    Court getCourt();
}
