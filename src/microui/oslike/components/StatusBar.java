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
public class StatusBar extends TextObject {

    protected int bgcolor = 0xCCCCCC;
    protected int color = 0x000000;
    protected String symbol = "% ";

    public StatusBar(Desktop win, String string, Font font) {
        super(string, font, win);
        this.h = this.h + window.padding*2;
        w = win.w;
        setLocation(0, win.h-font.getHeight()-win.padding*2);
    }

    public void setStyle(String symbol, int color, int bgcolor) {
        if(symbol != null)
            this.symbol = symbol;
        this.color = color;
        this.bgcolor = bgcolor;
    }

    public void paint(Graphics g, String string) {
        if(font != null)
            g.setFont(font);
        g.setColor(bgcolor);
        g.fillRect(x, y, w, h);
        g.setColor(color);

        g.drawString(symbol + string, x+window.padding, y+window.padding, ORIGIN);
    }

    public void paint(Graphics g) {
        g.setColor(0xCCCCCC);
        g.fillRect(x, y, w, h);
        g.setColor(0x000000);

        g.drawString(symbol + string, x+window.padding, y+window.padding, ORIGIN);
    }
}