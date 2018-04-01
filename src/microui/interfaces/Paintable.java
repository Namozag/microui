/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.interfaces;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author h
 */
public interface Paintable {
    public final int ORIGIN = Graphics.TOP | Graphics.LEFT;
    public final int CENTER = Graphics.TOP | Graphics.HCENTER;

    void paint(Graphics g);
    void paintShifted(Graphics g, int x, int y);
    
    void paintBorder(Graphics g);
    void paintShiftedBorder(Graphics g, int sx, int sy);
    void paintBackground(Graphics g);
}
