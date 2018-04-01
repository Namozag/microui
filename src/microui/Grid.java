/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.generic.GenericItem;

/**
 *
 * @author h
 */
public class Grid extends Canvas {

    int margin;
    Box box;
    int rowSize;
    int itemWidth, itemHeight;
    GenericItem[] items;
    boolean showBorder = true;
    Image background;

    public Grid(GenericItem[] items, int itemWidth, int itemHeight, Box box) {
        this.box = box;
        this.itemHeight = itemHeight;
        this.itemWidth = itemWidth;
        this.items = items;

        int im = itemWidth / 10;
        int c = box.w / itemWidth;
        int m = (box.w - c * itemWidth) / (c + 1);

        if (m < im) {
            c--;
            m = (box.w - c * itemWidth) / (c + 1);
        }

        margin = m;
        rowSize = c;

        System.out.println("Screen w: " + box.w + "  h: " + box.h);
        System.out.println("Grid count: " + items.length + "  w: " + itemWidth + "  h: " + itemHeight);
        System.out.println("row cells: " + rowSize + "  margin: " + margin);

        int x = -itemWidth;
        int y = -itemHeight;

        for (int i = 0; i < items.length; i++) {

            if (i % rowSize == 0) {
                System.out.println("---------");
                x = margin;
                y += margin + itemHeight;
            } else {
                x += margin + itemWidth;
            }

            items[i].setLocation(x + box.x, y + box.y);
            System.out.println("#" + i + "  x: " + x + "  y: " + y);

        }

    }

    public void setBackground(Image im) {
        this.background = im;
    }

    public void paintHorizontal(Graphics g) {
        g.setColor(0x000000);

        int y = margin;
        int x = (itemWidth + margin) * rowSize;
        int c = items.length / rowSize + 1;
        for (int i = 0; i < items.length; i++) {
            g.drawLine(margin, y, x, y);
            y += margin + itemHeight;
        }
    }

    public void paintVertical(Graphics g) {
        g.setColor(0x000000);

        int x = margin;
        int y = (itemHeight + margin) * rowSize;
        int c = rowSize + 1;
        for (int i = 0; i < c; i++) {
            g.drawLine(x, margin, x, y);
            x += margin + itemWidth;
        }
    }

    public void paintCellBorder(int index, Graphics g) {
        int r = index / rowSize;
        int c = index % rowSize - 1;

        int x = c * itemWidth + (c + 1) * (margin);
        int y = r * itemHeight + (r + 1) * (margin);

        g.setColor(0x000000);
        g.drawRect(x, y, itemWidth, itemHeight);

    }

    public void paintAllcellBorders(Graphics g) {
        for (int i = 0; i < items.length; i++) {
            items[i].paint(g);
        }
    }

    public void paintBorders(Graphics g) {
        paintVertical(g);
        paintHorizontal(g);
    }

    public void paint(Graphics g) {
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, box.w, box.h);
        for (int i = 0; i < items.length; i++) {
            items[i].paint(g);
        }
    }
}
