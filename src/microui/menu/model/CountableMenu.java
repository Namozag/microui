/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public class CountableMenu extends NavigatableMenu implements Countable {
    int[] count;

    public void countUp() {
        count[super.getPointer()]++;
    }

    public void countDown() {
        if(count[super.getPointer()] > 0)
            count[super.getPointer()]--;
    }

    public int[] getSelectionCount() {
        return count;
    }

}
