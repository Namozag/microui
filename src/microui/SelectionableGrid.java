/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microui;

import javax.microedition.lcdui.Graphics;
import microui.listeners.ClickListener;
import microui.generic.GenericItem;

/**
 *
 * @author h
 */
public class SelectionableGrid extends Grid {

    public GridSelector selector;
    int shift = 0;
    ClickListener clickListener;
    SelectionLisener selectionListener;
    SelectionLisener pointerListener;

    public SelectionableGrid(GenericItem[] items, int itemWidth, int itemHeight, Box box) {
        super(items, itemWidth, itemHeight, box);
        selector = new GridSelector(items.length, rowSize);
    }

    public void setSelectionListener(SelectionLisener l) {
        this.selectionListener = l;
    }

    public void setPointerListener(SelectionLisener l) {
        this.pointerListener = l;
    }

    public void paint(Graphics g) {
//        g.setColor(0xFFFFFF);
//        g.fillRect(0, 0, box.w, box.h);

        if(background != null)
            g.drawImage(background, 0, 0, Graphics.LEFT|Graphics.TOP);

        g.setColor(0x000000);
        for (int i = 0; i < items.length; i++) {
            items[i].paintShifted(g, 0, shift);
        }

        items[selector.getPointer()].paintShiftedBorder(g, 0, shift);
    }

    boolean keyLock = false;
    public void keyPressed(int keyCode) {
        if(keyLock == true)
            return;

        switch (getGameAction(keyCode)) {
            case LEFT:
                selector.back();
                break;
            case RIGHT:
                selector.next();
                break;
            case UP:
                selector.up();
                break;
            case DOWN:
                selector.down();
                break;
            case FIRE:
                if(selectionListener != null)
                    selectionListener.onSelect(selector.pointer);
                break;
        }

        if(pointerListener != null)
            pointerListener.onSelect(selector.pointer);

        // slide down
        if (items[selector.getPointer()].y + shift > box.y + box.h - itemHeight) {
            slideDown();
        }
//            shift -= itemHeight+margin;

        // slide up
        if (items[selector.getPointer()].y + shift < box.y) {
            slideUp();
//            shift += itemHeight + margin;
        }

        // 
        if (shift > 0) {
            shift = 0;
        }

        repaint();
    }

    public void slideDown() {
        new Slider(12, Slider.DOWN, Slider.DEACCELERATE).start();
    }

    public void slideUp() {
        new Slider(12, Slider.UP, Slider.DEACCELERATE).start();
    }

    class Slider extends Thread {

        public static final int NONE = 0;
        public static final int ACCELERATE = 1;
        public static final int DEACCELERATE = 2;

        public static final int UP = 11;
        public static final int DOWN = 12;

        int behaviour = NONE;
        int direction;
        int stepValue = 10;
        int step;

        public Slider(int step, int direction,  int behaviour) {

            this.stepValue = step;
            this.behaviour = behaviour;
            this.direction = direction;
            
            if(behaviour == ACCELERATE)
                this.step = 0;
            else
                this.step = stepValue;
        }

        public void run() {

            keyLock = true;

            boolean ON = true;
            int target;
            if(direction == DOWN)
                target = shift - itemHeight - margin;
            else
                target = shift + itemHeight + margin;
            while (ON) {
                // wait
                try { sleep(80); } catch (Exception ex) { }

                // check direction
                if(direction == DOWN)
                    shift -= step;
                else
                    shift += step;

                // check behaviour
                if ( behaviour == ACCELERATE && step < stepValue) {
                    step++;
                } else if ( behaviour == DEACCELERATE && step > 2) {
                    step--;
                }
                repaint();

                // check for break
                if(direction == DOWN && target > shift)
                    break;
                if(direction == UP && target < shift)
                    break;

                System.out.print("  " + shift);
            }
            shift = target;
            repaint();

            keyLock = false;
        }
    }
}
