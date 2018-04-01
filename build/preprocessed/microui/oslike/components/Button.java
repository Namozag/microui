/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import microui.STYLE;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public class Button extends LinkObject {

    public Button (String label, String string, Font font, Desktop win) {
        super(label, string, font, win);
    }

    public void paint(Graphics g) {
        if(font != null)
            g.setFont(font);
        if(isPointed())
            g.setColor(STYLE.colorBGPointed);
        else
            g.setColor(STYLE.colorBG);
        g.fillRect(x, y, w, h);

        if(isPointed())
            g.setColor(STYLE.colorTextPointed);
        else
            g.setColor(STYLE.colorText);
        
        super.paint(g);

        g.setColor(STYLE.colorSelector);
        g.drawRect(x, y, w, h);
    }

}
