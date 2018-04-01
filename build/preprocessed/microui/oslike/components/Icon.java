/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.oslike.Desktop;
import microui.utils.ImageManager;

/**
 *
 * @author h
 */
public class Icon extends Component {

    Image image;
    boolean showLabel = false;

    public Icon(String label, Image im, boolean showLabel, Desktop win) {
        super(label, win);
        this.image = im;
        this.showLabel = showLabel;
        h = image.getHeight();
        w = image.getWidth();
    }

    public Icon(String label, String imUrl, boolean showLabel, Desktop win) {
        this(label, ImageManager.get(imUrl), showLabel, win);
    }

    public Icon(String label, String imUrl, Desktop win) {
        this(label, ImageManager.get(imUrl), false, win);
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, ORIGIN);
        if(showLabel) {
            g.drawString(label, x+w/2, y+h, CENTER);
        }
        if(isPointed()) {
            g.setColor(0xFF0000);
            g.drawRect(x-1, y-1, w+2, h+2);
        }
    }

}