/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Graphics;
import microui.oslike.Desktop;

/**
 *
 * @author hany
 */
public class SwitchIcon extends Component {
    Icon on;
    Icon off;
    boolean ON = true;

    public SwitchIcon(String label, Icon on,Icon off, Desktop win) {
        super(label, win);
        this.on = on;
        this.off = off;

        setSize(on.w, on.h);
    }

    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        on.setLocation(x, y);
        off.setLocation(x, y);
    }

    public void paint(Graphics g) {
        if(ON)
            on.paint(g);
        else
            off.paint(g);
    }

    public void inverse() {
        ON = !ON;
    }

    public void fire() {
//        super.fire();
        inverse();
        if(ON)
            on.fire();
        else
            off.fire();
    }

    public boolean isSelected() {
        return ON;
    }

}
