/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

/**
 *
 * @author h
 */
public interface Markable {

    boolean ENABLE_MULTIPLE_SELECTION = true;

    void mark();
    void mark(int index);
    void unmark();
    void unmark(int index);
    void invertSelection();
    boolean[] getSelectionFlags();
    void setMarkListener(MarkListener sl);
}
