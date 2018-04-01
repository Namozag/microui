/*
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.utils;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author h
 */
public class TextPainter {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int CENTER = 3;

    String[] lines;
    public Font font;
    public int width;
    int lineSpacing;
    int alignment = LEFT;

    public TextPainter(String[] lines, int width) {
        this.lines = lines;
        this.width = width;
        font = Font.getDefaultFont();

        build();
    }

    public TextPainter(String text, Font font, int width) {
        this.width = width;
        this.font = font;
        lines = TextManager.getParagraphArray(text, font, width);

        build();
    }

    private void build() {
        setLineSpacingRatio(50);
    }

    public void setLineSpacingRatio(int ratio) {
        setLineSpacing( font.getHeight() * ratio / 100 );
    }

    public void setLineSpacing(int space) {
        lineSpacing = space;
    }

    public void setAlignment(int align) {
        this.alignment = align;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return (lines.length + lineSpacing) * font.getHeight();
    }

    public void paint(Graphics g, int x, int y)
    {
        paint(g, x, y, 0, lines.length);
    }

    public void paint(Graphics g, int x, int y, int start, int count)
    {
        int align = Graphics.TOP;
//        align |= (alignLeft)? Graphics.LEFT: Graphics.RIGHT;

        if(alignment == LEFT)
            align |= Graphics.LEFT;
        else if(alignment == RIGHT) {
            align |= Graphics.RIGHT;
            x = width - x;
        }
        else if(alignment == CENTER) {
            align |= Graphics.HCENTER;
            x = width/2;
        }

        for(int i=start; i<start+count; i++)
        {
            System.out.println( lines[i] );
            g.drawString(lines[i], x, y , align);
            y += (font.getHeight() + lineSpacing);
        }
    }

    Image image;
    public void generateImage(int w, Font font, boolean alignLeft)
    {
        image = Image.createImage(w, 10*lines.length);
        Graphics g = image.getGraphics();
        paint(g, 0, 0);
    }

}
