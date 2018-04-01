/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.canvas.items;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author h
 */
public class Label extends MicroItem {
    
    private String text;

    public Label(String text, Font font, int width, int height) {
        this.text = text;
        this.width = width;
        this.height = height;

        if(font != null )
            this.font = font;
        else
            this.font = Font.getDefaultFont();
    }

    public int getMinimumWidth() {
        return font.stringWidth(text);
    }

    public int getMinimumHeight() {
        return font.getHeight();
    }

    public int getWidth(int height) {
        return width;
    }

    public int getHeight(int width) {
        return height;
    }

    public void paint(Graphics g, int y) {
        g.drawString(text, 0, y, Graphics.TOP|Graphics.LEFT);
    }

    public void keyPressed(int keyCode) {
    }

}
