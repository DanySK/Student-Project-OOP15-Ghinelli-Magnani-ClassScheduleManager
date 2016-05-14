/**
 * 
 */
package model_interface;

import model.Court;

/**
 * @author Martina Magnani
 *  
 */
public interface ITeaching extends java.io.Serializable{
    public String getName();
    public String getYear();
    public Court getCourt();
}
