/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public class MarkableMenu extends NavigatableMenu implements Markable {
    private boolean[] selection;
    private MarkListener markListener;

    public void build() {
        selection = new boolean[ super.size() ];
    }

    public void mark() {
        if(markListener != null)
            markListener.onMark(itemAt(super.getPointer()));
        selection[super.getPointer()] = true;
    }

    public void unmark() {
        if(markListener != null)
            markListener.onUnmark(itemAt(super.getPointer()));
        selection[super.getPointer()] = false;
    }

    public void invertSelection() {
        selection[super.getPointer()] = ! selection[super.getPointer()];
    }

    public boolean[] getSelectionFlags() {
        return selection;
    }

    public void setMarkListener(MarkListener sl) {
        this.markListener = sl;
    }

    public void mark(int index) {
        selection[index] = true;
    }

    public void unmark(int index) {
        selection[index] = false;
    }

}