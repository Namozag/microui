/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu;

import microui.menu.model.MenuClass;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author h
 */
public class ExtendedMenuCanvasOld extends Canvas {
    MenuClass menu;
    int ORIGIN = Graphics.TOP | Graphics.LEFT;

    int itemH, itemW, itemHS, itemWS;
    int shiftT, shiftB;
    int shift;
    int slideLimit  = 40, slideFactor = 40;

    int pointer = 0;

    public ExtendedMenuCanvasOld(MenuClass menu) {
        this.menu = menu;
        itemH = menu.itemAt(0).image.getHeight();
        itemW = menu.itemAt(0).image.getWidth();
        itemHS = itemH / 10;
        itemWS = itemW / 10;
        shiftT = 20;

        setFullScreenMode(true);
    }

    int getY(int i) {
        return i*(itemH + itemHS) + shiftT + shift;
    }

    public void slide(final int vshift) {

        final int from = shift;
        final int to   = shift + vshift;

        final int inc = 2;

        System.out.println("SLIDING " + from + " > " + to + "  : " + vshift);
        Thread slider = new Thread() {
            public void run() {
                while(true) {
                    if(vshift > 0) {
                        shift += inc;
                        if(to < shift)
                            break;
                    }
                    else {
                        shift -= inc;
                        if(to > shift)
                            break;
                    }
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                shift = to;
                repaint();
            }
        };
        slider.start();
    }

    protected void paint(Graphics g) {

        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0x000000);

        for(int i=0; i<menu.size(); i++) {
            int y = getY(i);

            if(pointer == i)
                g.setColor(0xCCCCFF);
            else
                g.setColor(0xCCCCCC);
            g.fillRect(itemWS, y, getWidth() - itemWS*2, itemH);

//            g.drawLine(0, y, getWidth(), y);

            g.setColor(0x000000);
            g.drawImage(menu.itemAt(i).image, itemWS*3, y, ORIGIN);
            g.drawString(menu.itemAt(i).title, itemW + itemWS*4, y, ORIGIN);

            if(pointer == i) {
                g.setColor(0xFFFF00);
                g.fillRect(itemWS, y, itemWS, itemH);
                g.fillRect(getWidth() - itemWS*2, y, itemWS, itemH);
                g.setColor(0x8888CC);
                g.drawRect(itemWS - itemWS/2, y - itemHS/2, getWidth() - itemWS*2 + itemWS, itemH + itemHS);
            }
        }

    }

    public void keyPressed(int keyCode) {
        int key = getGameAction(keyCode);

        switch(key) {
            case UP :
                pointer--;
                break;
            case DOWN :
                pointer++;
               break;
        }

        if(getY(pointer) > getHeight() - slideLimit*2)
            slide(-slideFactor);
        else if(getY(pointer) < slideLimit)
            slide(slideFactor);
        else
            repaint();


    }

}
