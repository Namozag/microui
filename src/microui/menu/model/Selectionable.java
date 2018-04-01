/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public interface Selectionable {
    boolean ENABLE_SELECTION = true;

    void select();
    void deSelect();
    int  getSelected();
    void setSelectionListener(SelectionListener sl);
}
