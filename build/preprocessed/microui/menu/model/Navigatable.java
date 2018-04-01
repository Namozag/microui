/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public interface Navigatable {
    boolean next();
    boolean back();
    boolean point(int index);
    int getPointer();
}
