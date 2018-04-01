/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public class SelectionableMenu extends NavigatableMenu implements Selectionable {
    private int selector = -1;
    private SelectionListener selectionListener;

    public void select() {
        selector = super.getPointer();
        if(selectionListener != null)
            selectionListener.onSelect(itemAt(selector), selector);
    }

    public void deSelect() {
        selector = -1;
//        if(selectionListener != null)
//            selectionListener.onSelect(itemAt(selector), selector);
    }
    public int getSelected() {
        return selector;
    }

    public void setSelectionListener(SelectionListener sl) {
        this.selectionListener = sl;
    }


}