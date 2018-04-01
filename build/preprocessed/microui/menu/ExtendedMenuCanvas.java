/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu;

import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import microui.STYLE;
import microui.menu.model.Countable;
import microui.menu.model.Markable;
import microui.menu.model.NavigatableMenu;
import microui.menu.model.Selectionable;
import microui.utils.TextManager;

/**
 *
 * @author h
 */
public class ExtendedMenuCanvas extends Canvas {
    NavigatableMenu menu;
    int ORIGIN = Graphics.TOP | Graphics.LEFT;

    int itemH, itemW, itemHS, itemWS;
    int shiftT, shiftB;
    int shift;
    int slideLimit  = 40, slideFactor = 40;

    Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

    // settings
    boolean LOOSE_SELECTOR_ON_KEY_PRESS = false;
    public boolean SELECT_ON_POINT     = false;
    boolean MARK_ON_FIRE      = false;
    boolean ACTION_ON_FIRE      = false;
    public boolean OPEN_EMPTY_ITEMS    = true;
    boolean STICK_ON_SELECT     = false;
    public boolean ENABLE_INFO         = true;

    public ExtendedMenuCanvas(NavigatableMenu menu) {
        this.menu = menu;
        itemH = menu.itemAt(0).image.getHeight();
        itemW = menu.itemAt(0).image.getWidth();
        itemHS = itemH / 10;
        itemWS = itemW / 10;
        shiftT = 20;
        shift = shiftT;

        setFullScreenMode(true);
    }

    int selectorH = 0;
    private int getY(int i) {
        int s = i*(itemH + itemHS) +  shift;
        if( (menu instanceof Selectionable) && ENABLE_INFO && i > ((Selectionable) menu).getSelected() && lines != null)
            s += lines.size() * font.getHeight() + itemHS;
        return s;
    }

    private void slide(final int vshift) {

        final int from = shift;
        final int to   = shift + vshift;

        final int inc = 2;

        System.out.println("SLIDING " + from + " > " + to + "  : " + vshift);
        Thread slider = new Thread() {
            public void run() {
                while(true) {

                    if(shift > shiftT) {
                        shift = shiftT;
                        repaint();
                        return;
                    }

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
                calibrate();
            }
        };
        slider.start();
    }

    public void paint(Graphics g) {

        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0x000000);

        for(int i=0; i<menu.size(); i++) {
            int y = getY(i);

            if(menu.getPointer() == i)
                g.setColor(STYLE.colorBGPointed);
            else
                g.setColor(STYLE.colorBG);
            g.fillRect(itemWS, y, getWidth() - itemWS*2, itemH);

//            g.drawLine(0, y, getWidth(), y);
            g.setColor(STYLE.colorTextPointed);
            g.drawImage(menu.itemAt(i).image, itemWS*3, y, ORIGIN);
            g.drawString(menu.itemAt(i).title, itemW + itemWS*4, y, ORIGIN);

            // selection mode
            if(menu instanceof Markable) {
                g.setColor(STYLE.colorSelector);
                if( ((Markable)menu).getSelectionFlags()[i])
                    g.fillRect(getWidth()-itemWS*5, y + itemH/3, itemH/3, itemH/3);
                else
                    g.drawRect(getWidth()-itemWS*5, y + itemH/3, itemH/3, itemH/3);
            }
            if(menu instanceof Countable && ((Countable)menu).getSelectionCount()[i] > 0) {
                g.setColor(STYLE.colorSelector);
//                g.drawRect(getWidth()-itemWS*5, y + itemH/3, itemH/3, itemH/3);
                g.drawString(String.valueOf( ((Countable)menu).getSelectionCount()[i] ), getWidth()-itemWS*5, y + itemH/3, ORIGIN);
            }

            if(menu.getPointer() == i) {
                g.setColor(0xFFFF00);
                g.fillRect(itemWS, y, itemWS, itemH);
                g.fillRect(getWidth() - itemWS*2, y, itemWS, itemH);
//                g.setColor(0x8888CC);
//                g.drawRect(itemWS - itemWS/2, y - itemHS/2, getWidth() - itemWS*2 + itemWS, itemH + itemHS);
            }

            if(menu instanceof Selectionable) {
                if(ENABLE_INFO && ((Selectionable)menu).getSelected() == i && lines != null) {
                    paintInfo(g, i);
                }
            }
        }
    }

    private void paintInfo(Graphics g, int i) {
        int y = getY( i ) + itemH + itemHS;

        g.setColor(STYLE.colorBGSELECTED);
        g.fillRect(itemWS, y-itemHS, getWidth() - itemWS*2, lines.size()*font.getHeight() + itemHS);

        g.setColor(STYLE.colorTextSELECTED);
//                g.drawRect(itemWS + itemWS/2, y-itemHS + itemHS/2, getWidth() - itemWS*2 - itemWS, lines.size()*font.getHeight());

        for(int l=0; l<lines.size(); l++) {
            g.drawString( (String) lines.elementAt(l), itemWS*2, y + l*font.getHeight(), ORIGIN);
        }
    }

    private void calibrate() {
        if(getY(menu.getPointer()) > getHeight() - slideLimit*2){
            slide(-slideFactor);
        } else if(getY(menu.getPointer()) < slideLimit) {
            slide(slideFactor);
//        } else if(getY(0) > shiftT){
//            slide(-slideFactor);
        } else
            repaint();
    }

    Vector lines;

    public void keyPressed(int keyCode) {
        int key = getGameAction(keyCode);

        if(menu instanceof Selectionable) {
            if(LOOSE_SELECTOR_ON_KEY_PRESS)
                ((Selectionable)menu).deSelect();

            if(STICK_ON_SELECT && ((Selectionable)menu).getSelected() == menu.getPointer()) {
                if(key == FIRE) {
                    ((Selectionable)menu).deSelect();
                    repaint();
                }
                return;
            }
        }

        switch(key) {
            case UP     : menu.back(); break;
            case DOWN   : menu.next(); break;
            case RIGHT  :
                if(menu instanceof Markable)
                    ((Markable)menu).mark();
                if (menu instanceof Countable)
                    ((Countable)menu).countUp();
                break;
            case LEFT   :
                if(menu instanceof Markable)
                    ((Markable)menu).unmark();
                if (menu instanceof Countable)
                    ((Countable)menu).countDown();
                break;
            case FIRE   :
                if( menu instanceof Selectionable){
                    if(ENABLE_INFO)
                        showInfo();
                    else
                        ((Selectionable)menu).select();
//                if(menu instanceof Selectionable)
//                    ((Selectionable)menu).select();
                }

                if( menu instanceof Markable && MARK_ON_FIRE)
                    ((Markable)menu).invertSelection();

                
                break;
        }

        if(menu instanceof Selectionable && SELECT_ON_POINT) {
             ((Selectionable)menu).select();
            lines = TextManager.getParagraphLines(menu.itemAt( ((Selectionable)menu).getSelected()).info, font, getWidth() - itemWS*2);
        }
        calibrate();
    }

    private void showInfo() {
        System.out.println("selected: " + ((Selectionable)menu).getSelected());
        System.out.println("pointer: " + menu.getPointer());
        if(((Selectionable)menu).getSelected() != menu.getPointer()) {
            lines = TextManager.getParagraphLines(menu.itemAt(menu.getPointer()).info, font, getWidth() - itemWS*2);
            if( OPEN_EMPTY_ITEMS ||
                    ( lines.size() > 0 && ( (String) lines.elementAt(0) ).trim().length()>0 ) )
                ((Selectionable)menu).select();
            else
                ((Selectionable)menu).deSelect();
        }
        else
            ((Selectionable)menu).deSelect();

        System.out.println("Selector : " + menu.getPointer() + " > " + ((Selectionable)menu).getSelected());
        calibrate();
    }

    private void slideShow() {

        Thread runner = new Thread() {
            public void run() {
                boolean FORWARD = true;
                
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(FORWARD) {
                        if( !menu.next() )
                            FORWARD = false;
                    } else {
                        if( !menu.back() )
                            FORWARD = true;
                    }
                    
                    showInfo();
                    calibrate();

                }
            }
        };
        runner.start();

    }

}