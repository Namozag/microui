/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Graphics;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public abstract class DummyObject extends Item {

    public DummyObject(Desktop win) {
        super(win);
    }

    public void paint(Graphics g) {
    }

    public void keyPressed(int keyCode) {
    }

    public boolean isPointed() {
        return false;
    }

    public boolean isPointed(Cursor c) {
        return false;
    }
    
}
