/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public class TitleObject extends TextObject {

    public TitleObject(String string, Font font, Desktop win) {
        super(string, font, win);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(x, y+h, w, 2);
    }


}
