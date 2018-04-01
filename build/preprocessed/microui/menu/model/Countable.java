/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public interface Countable {

    boolean ENABLE_COUNT = false;

    void countUp();
    void countDown();
    int[] getSelectionCount();
}
