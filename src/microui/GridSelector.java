/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui;

/**
 *
 * @author h
 */
public class GridSelector extends Selector {
    int rowSize = size;

    public GridSelector(int size, int rowSize) {
        super(size);
        this.rowSize = rowSize;
    }

    public boolean up() {
        if(pointer >= rowSize)
        {
            pointer -= rowSize;
            return true;
        }
        else if(!stopAtEnd)
            pointer = size-rowSize+pointer;
        return false;
    }

    public boolean down()
    {
        if(pointer < size-rowSize)
        {
            pointer += rowSize;
            return true;
        }
        else if(!stopAtEnd)
            pointer = pointer%rowSize;
        return false;
    }

}
