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
public class ObjectsMenu extends Component {
    Component[] objects;
    boolean expanded = false;

    public ObjectsMenu(String label, Component[] objects, int w, Desktop window) {
        super(label, window);
        this.objects = objects;
        int shift = 0;
        this.w = w;
        this.h = objects.length * objects[0].h;
        for(int i=0; i<objects.length; i++) {
            objects[i].setLocation(x, y + shift);
            objects[i].w = w;
            shift += objects[i].h;
        }
    }

    public void paint(Graphics g) {
        objects[0].paint(g);

        if(expanded) {
           g.drawRect(objects[0].x -1, objects[0].y -1, w +2, objects[0].h +2);
        }

        if(expanded) {
            for(int i=1; i<objects.length; i++) {
                objects[i].paint(g);
//                if(objects[i].isPointed())
//                    window.cursor.hover();
            }
        }
    }

    public void fire() {

        if (objects[0].isPointed() ) {
            expanded = !expanded;
        }
    }

}
