package microui.lcd;

import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class SelectionBox extends CustomItem
{
    int width;
    int height;
    String title;
    Submitable form;

    public SelectionBox(String title, int width, int height, Submitable form) {
        super(null);
        this.width = width;
        this.height = height;
        this.title = title;
        this.form = form;
    }

    public SelectionBox(String title, Submitable form) {
        super(null);
        this.width  = Font.getDefaultFont().stringWidth(title) + 10;
        this.height = Font.getDefaultFont().getHeight() + 10;
        this.title = title;
        this.form = form;
    }

    protected int getMinContentWidth() {
        return width;
    }

    protected int getMinContentHeight() {
        return height;
    }

    protected int getPrefContentWidth(int height) {
        return width;
    }

    protected int getPrefContentHeight(int width) {
        return height;
    }

    protected void paint(Graphics g, int w, int h) {
        g.setColor(0x000000);
        g.fillRect(1, 1, width-2, height-2);
        g.setColor(0xFFFFFF);
        g.drawRect(3, 3, width-6, height-6);
        g.drawString(title, 5, 5, Graphics.TOP|Graphics.LEFT);
    }

    protected void keyPressed(int keyCode)
    {
        form.submit();
    }
}