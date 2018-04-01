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
public class ChoiceIcons extends Component{
    Icon[] choices;

    public ChoiceIcons(String label, Icon[] choices, Desktop win) {
        super(label, win);
        this.choices = choices;
    }

    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        int ix = x;
        int iy = y+window.padding;
        for(int i=0; i<choices.length; i++) {
            ix += window.padding;
            choices[i].setLocation(ix, iy);
            ix += choices[i].w;
        }
        setSize(ix+window.padding-x, y + window.padding*2);
    }

    public void paint(Graphics g) {
        for(int i=0; i<choices.length; i++) {
            choices[i].paint(g);
        }
    }

    public void fire() {
        
    }


}
