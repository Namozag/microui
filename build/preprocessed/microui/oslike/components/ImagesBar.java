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
public class ImagesBar extends Component {

    Icon[] objects;

    public ImagesBar(String label, Icon[] objects, int x, int y, Desktop window) {
        super(label, window);
        this.objects = objects;
        int shift = 0;
        for(int i=0; i<objects.length; i++) {
            objects[i].setLocation(x + shift, y);
            shift += objects[i].w;
        }
        
    }

    public void paint(Graphics g) {
        for(int i=0; i<objects.length; i++) {
            objects[i].paint(g);
        }
    }

}
