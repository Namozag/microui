/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.canvas.MicroCanvas;
import microui.generic.GenericItem;

/**
 *
 * @author h
 */
public class ViewCanavs extends MicroCanvas {

    GenericItem[] items;
    int margin = 5;
    Image background;
    int height;
    int shift;
    int space =40;
    int behaviour = ACCELERATE;

    public ViewCanavs(GenericItem[] items) {
        this.items = items;

        int h = margin;
        for (int i = 0; i < items.length; i++) {
            h += items[i].h;
        }
        height = h + margin;
        System.out.println("H: " + h);
    }

    public void setSlideRatio(int ratio) {
        setSlide( getWidth() * ratio/100 );
    }

    public void setSlide(int slide) {
        this.space = slide;
    }

    public void setBehaviour(int b) {
        this.behaviour = b;
    }

    public void setBackground(Image im) {
        this.background = im;
    }

    protected void paint(Graphics g) {

        super.paint(g);

        if(background != null)
            g.drawImage(background, 0, 0, Graphics.TOP|Graphics.LEFT);

        int s = margin + shift;
        for (int i = 0; i < items.length; i++) {
            g.setColor(style.colorText);
//            items[i].paintShifted(g, (getWidth() - items[i].w) / 2 , s);
            items[i].paintShifted(g, 0, s);

//            g.setColor(style.colorSelector);
//            items[i].paintShiftedBorder(g, 0, s);
            s += items[i].h;
        }
    }

    protected void keyPressed(int keyCode) {
        System.out.println("K P");
        switch(getGameAction(keyCode)) {
            case UP:
                if(shift > -margin)
                    return;
                new Slider(12, UP, behaviour).start();
                break;
            case DOWN:
                if(shift < getHeight()-height)
                    return;
                System.out.println("DOWN");
                new Slider(12, DOWN, behaviour).start();
                break;
        }
    }
    boolean keyLock;


    public static final int NONE = 0;
    public static final int ACCELERATE = 1;
    public static final int DEACCELERATE = 2;

    class Slider extends Thread {

        int behaviour = NONE;
        int direction;
        int stepValue = 10;
        int step;

        public Slider(int step, int direction, int behaviour) {

            this.stepValue = step;
            this.behaviour = behaviour;
            this.direction = direction;

            if (behaviour == ACCELERATE) {
                this.step = 0;
            } else {
                this.step = stepValue;
            }
        }

        public void run() {

            keyLock = true;

            boolean ON = true;
            int target;
            if (direction == DOWN) {
                target = shift - space;
            } else {
                target = shift + space;
            }
            while (ON) {
                // wait
                try {
                    sleep(80);
                } catch (Exception ex) {
                }

                // check direction
                if (direction == DOWN) {
                    shift -= step;
                } else {
                    shift += step;
                }

                // check behaviour
                if (behaviour == ACCELERATE && step < stepValue) {
                    step++;
                } else if (behaviour == DEACCELERATE && step > 2) {
                    step--;
                }
                repaint();

                // check for break
                if (direction == DOWN && target > shift) {
                    break;
                }
                if (direction == UP && target < shift) {
                    break;
                }

            }
            shift = target;
            repaint();

            keyLock = false;
        }
    }
}
