/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.components;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.utils.ImageManager;

/**
 *
 * @author h
 */
public class Icon extends Component {

    Image image;
    boolean showLabel = false;

    public Icon(String label, Image im, boolean showLabel) {
        super(label);
        this.image = im;
        this.showLabel = showLabel;
        h = image.getHeight();
        w = image.getWidth();
    }

    public Icon(String label, String imUrl, boolean showLabel) {
        this(label, ImageManager.get(imUrl), showLabel);
    }

    public Icon(String label, String imUrl) {
        this(label, ImageManager.get(imUrl), false);
    }

    public void setLabelVisibility(boolean b) {
        showLabel = b;
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
    
    public void paintShifted(Graphics g, int sx, int sy) {
        g.drawImage(image, x +sx, y +sy, ORIGIN);
        if(showLabel) {
            g.drawString(label, x+w/2 +sx, y+h +sy, CENTER);
        }
        if(isPointed()) {
            g.setColor(0xFF0000);
            g.drawRect(x-1 +sx, y-1 +sy, w+2, h+2);
        }
    }

}