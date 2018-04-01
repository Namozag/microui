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
public class LinkObject extends Component {

    Font font;
    String string;

    public LinkObject(String label, String string, Font font, Desktop win) {
        super(label, win);
        this.string = string;
        this.font = font;
        h = font.getHeight() + win.padding*2;
        w = font.stringWidth(string) + win.padding*2;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void paint(Graphics g) {
        if(font != null)
            g.setFont(font);
        g.setColor(0x000000);
        g.drawString(string, x+window.padding, y+window.padding, ORIGIN);
    }

    public void keyPressed(int keyCode) {
    }
}