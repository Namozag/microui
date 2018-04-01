/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.canvas;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import microui.generic.Style;
import microui.utils.TextManager;
import microui.utils.TextPainter;
import microui.utils.TextReader;

/**
 *
 * @author h
 */
public class TextCanvas extends MicroCanvas {
    String title;
    String[] lines;
    TextPainter textPainter;
    Font font;
    int margin = 5;
    int maxLines = 5;
    int offset = 0;

    public TextCanvas(String title, String text) {
        build(title, text);
    }

    public TextCanvas(String title, String url, int x) {
        TextReader reader = new TextReader(url);
        build(title, reader.getText());
    }

    private void build(String title, String text) {
        this.title = title;
        font = Font.getDefaultFont();
        lines = TextManager.getParagraphArray(text, font, getWidth()-2*margin);
        textPainter = new TextPainter(lines, getWidth()-2*margin);
        textPainter.setAlignment(TextPainter.RIGHT);

        style = new Style();
    }

    protected void paint(Graphics g) {

        super.paint(g);

        int sw = font.stringWidth(title);
        int h = font.getHeight();

        g.setColor(style.colorBGPointed);
        g.fillRect( (getWidth() - sw)/2 - margin, margin-2, sw + margin*2, h+4);

        g.setColor(style.colorText);
        g.drawString(title, getWidth()/2, margin, Graphics.HCENTER|Graphics.TOP);

        textPainter.paint(g, margin, margin*3+h, offset, maxLines);
    }

    protected void keyPressed(int keyCode) {
        int key = getGameAction(keyCode);
        switch (key) {
            case UP:
                if(offset > 0)
                    offset--;
                break;
            case DOWN:
                if(offset+maxLines < lines.length)
                    offset++;
                break;
        }
        repaint();
    }

}
