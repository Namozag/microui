/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import microui.listeners.ClickListener;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public abstract class Component extends Item implements ClickListener {
    public String label;

    private ClickListener mouseListener;

    public Component(String label, Desktop win) {
        super(win);
        this.label = label;
    }

    public void keyPressed(int keyCode) {
        // empty imlpementation
        int key = window.getGameAction(keyCode);
        if(key == Desktop.FIRE)
            fire();
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
