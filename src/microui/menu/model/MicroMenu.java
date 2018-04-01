package microui.menu.model;

import java.util.Vector;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class MicroMenu
{

    public Vector items = new Vector();
    
    public  boolean stopAtEnd   = true;
//    private int pointer = 0, selector = -1;
//    private boolean[] selection;
//    private int[]   count;

    public MicroMenu()
    {
    }

    public MicroMenu(String path, int count)
    {
        MenuImporter.fillImageMenu(this, path, count);
    }

    public  void addItem(MicroItem item)
    {
        items.addElement(item);
    }

    public MicroItem itemAt(int index)
    {
        return (MicroItem) items.elementAt(index);
    }
    
    public int size()
    {
        return  this.items.size();
    }

}