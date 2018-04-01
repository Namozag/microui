/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public class ComponentsGroup extends Component {
    Vector components;

    public ComponentsGroup(String label, Desktop window) {
        super(label, window);
        components = new Vector();
    }

    public ComponentsGroup(String label, Desktop window, Vector components) {
        super(label, window);
        this.components = components;
    }

    public void paint(Graphics g) {
        for(int i=0; i<components.size(); i++) {
            
        }
    }

}
