/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.components;

import javax.microedition.lcdui.Graphics;
import microui.generic.GenericItem;
import microui.listeners.ClickListener;


/**
 *
 * @author h
 */
public abstract class Component extends GenericItem implements ClickListener {
    public String label;

    private ClickListener mouseListener;

    public Component(String label) {
        this.label = label;
    }

    public void keyPressed(int keyCode) {
        // empty imlpementation
//        int key = Canvas.getGameAction(keyCode);
//        if(key == Window.FIRE)
//            fire();
    }

    public void setClickListener(ClickListener ml) {
        this.mouseListener = ml;
    }

//    abstract public void fire();
    public void fire() {
        if(mouseListener != null)
            mouseListener.onClick();
    }

    public void onClick() {
        // empty implementation
    }

}
