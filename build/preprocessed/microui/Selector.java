/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui;

/**
 *
 * @author h
 */
public class Selector {
    public  boolean stopAtEnd   = true;
    protected int pointer = 0;
    int size;

    public Selector(int size) {
        this.size = size;
    }

    public boolean next()
    {
        if(pointer < size-1)
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
            pointer = size-1;
        return false;
    }

    public boolean point(int itemIndex)
    {
        if(itemIndex > 0 && itemIndex < size)
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
