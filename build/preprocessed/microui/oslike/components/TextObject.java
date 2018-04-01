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
public class TextObject extends DummyObject {

    Font font;
    String string;

    public TextObject(String string, Font font, Desktop win) {
        super(win);
        this.string = string;
        if(font == null)
            this.font = win.font;
        else
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