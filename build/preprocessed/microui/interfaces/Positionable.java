/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.interfaces;

/**
 *
 * @author h
 */
public interface Positionable {
    int getX();
    int getY();
    int getW();
    int getH();

    void setLocation(int x, int y);
    void setSize(int w, int h);
}
