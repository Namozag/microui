/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.components;

import microui.generic.GenericItem;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import microui.utils.TextManager;

/**
 *
 * @author h
 */
public class TextItem extends GenericItem {

    Font font;
    String string;
    String[] text;
    int width;

    public TextItem(String string, Font font, int width) {
        this.width = width;
        if(font == null)
            this.font = Font.getDefaultFont();
        else
            this.font = font;

        setString(string);
    }

    public void setString(String string) {
        this.string = string;
        this.text = TextManager.getParagraphArray(string, font, width);
        h = font.getHeight() * text.length;
//        System.out.println("h: " + h);
    }

    public void paint(Graphics g) {
        if(font != null)
            g.setFont(font);
        g.setColor(0x000000);

        int shift = 0;
        for(int i=0; i<text.length; i++) {
            g.drawString(text[i], x, y + shift, ORIGIN);
            shift += font.getHeight();
        }

    }

    public void keyPressed(int keyCode) {
    }

    public void paintShifted(Graphics g, int sx, int sy) {
        if(font != null)
            g.setFont(font);
        g.setColor(0x000000);

        int shift = 0;
        for(int i=0; i<text.length; i++) {
            g.drawString(text[i], x +sx, y +sy + shift, ORIGIN);
            shift += font.getHeight();

//            System.out.println("X: " + (x +sx) + "   Y: " +  (y +sy + shift) );
        }
    }


}