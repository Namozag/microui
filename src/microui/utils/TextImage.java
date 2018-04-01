/*
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.utils;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author h
 */
public class TextImage {
    Vector lines;
    Font font;
    int width;

    public TextImage(Vector lines) {
        this.lines = lines;
    }

    public TextImage(String text, Font font, int width) {
        this.width = width;
        this.font = font;
        lines = TextManager.getParagraphLines(text, null, width);
    }

    Image image;

    private String getLine(int index)
    {
        return (String) lines.elementAt(index);
    }


    public void paint(Graphics g, int x, int y, boolean alignLeft)
    {
        int align = Graphics.TOP;
        align |= (alignLeft)? Graphics.LEFT: Graphics.RIGHT;
        for(int i=0; i<lines.size(); i++)
        {
            System.out.println( getLine(i) );
            if(alignLeft)
                g.drawString(getLine(i), x, y + i*10, align);
            else
                g.drawString(getLine(i), x, y + i*10, align);
        }
    }

    public void paint(Graphics g, int x, int y, boolean alignLeft, int start, int limit)
    {
        int align = Graphics.TOP;
        align |= (alignLeft)? Graphics.LEFT: Graphics.RIGHT;
        for(int i=start; i<limit; i++)
        {
//            System.out.println( getLine(i) );
            g.drawString(getLine(i), x, y + i*10, align);
        }
    }

    public void generateImage(int w, Font font, boolean alignLeft)
    {
        image = Image.createImage(w, 10*lines.size());
        Graphics g = image.getGraphics();
        paint(g, 0, 0, alignLeft);
    }

}
