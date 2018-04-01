/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public class NavigatableMenu extends MicroMenu implements Navigatable {

    private int pointer = 0;

    public boolean next()
    {
        if(pointer < items.size()-1)
        {
            pointer++;
            return true;
        }
        else if(!stopAtEnd)
            pointer = 0;
        return false;
    }

    public boolean back()
    {
        if(pointer > 0)
        {
            pointer--;
            return true;
        }
        else if(!stopAtEnd)
            pointer = items.size() - 1;
        return false;
    }

    public boolean point(int itemIndex)
    {
        if(itemIndex > 0 && itemIndex < items.size())
        {
            this.pointer   = itemIndex;
            return true;
        }
        return false;
    }

    public int getPointer() {
        return pointer;
    }
}