package microui.menu.model;

import java.util.Vector;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class MenuClass implements Countable, Selectionable, Markable, Navigatable
{
    public  int     menuIndex   = 0;
    public  String  path;
    public  boolean stopAtEnd   = true;
    private int pointer = 0, selector = -1;
    private boolean[] selection;
    private int[]   count;

    private boolean imageCashed = false;

//    public MenuClass()
//    {
//        item = new ScreenItem();
//    }
//
//    public void setItem(ScreenItem i)
//    {
//        this.item = i;
//    }

    public MenuClass(int index)
    {
        this.menuIndex  = index;
    }

    public void build () {
        if(ENABLE_MULTIPLE_SELECTION)
            selection = new boolean[size()];
        if(ENABLE_COUNT)
            count = new int[size()];
    }

    public MenuClass(String path, int count)
    {
        this.path   = path;
        build();
    }

    public boolean next()
    {
        if(pointer < items.size()-1)
        {
            pointer++;
            return true;
        }
        else if(!stopAtEnd)
            pointer = 0;
        // else : do nothing
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
        // else : do nothing
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

    public  void addItem(MicroItem item)
    {
        items.addElement(item);
    }

    public MicroItem itemAt(int index)
    {
        return (MicroItem) items.elementAt(index);
    }

    public void mark() {
        selection[pointer] = true;
    }

    public void unmark() {
        selection[pointer] = false;
    }

    public void invertSelection() {
        selection[pointer] = ! selection[selector];
    }

    public void select() {
        selector = pointer;
    }

    public void deSelect() {
        selector = -1;
    }

    public void countUp() {
        count[pointer]++;
    }

    public void countDown() {
        if(count[pointer] > 0)
            count[pointer]--;
    }
    
    public int size()
    {
        return  this.items.size();
    }

    public int getPointer() {
        return pointer;
    }

    public int getSelected() {
        return selector;
    }

    public boolean[] getSelectionFlags() {
        return selection;
    }

    public int[] getSelectionCount() {
        return count;
    }

    public void fillImageMenu(int count)
    {
        path = "/images/icons/menu" + menuIndex + "/";

        String[] names = {"one one", "two two", "three three", "four four", "five five"};

        for(int i=0; i<count; i++)
        {
            addItem(new MicroItem(names[i], path + (i+1) + ".png", MicroItem.LOAD_IMAGE_OK));
        }
    }

//    public void build()
//    {
//        for(int i=0; i<this.size(); i++)
//        {
//            addItem(new ItemClass("welcome", path + (i+1) + ".png", true));
//            System.out.println("image added: " + path + (i+1) + ".png" );
//        }
//    }

    public Vector items   = new Vector();

    public void addCsvVector(Vector vector)
    {
        MicroItem item;
        int start, end;
        for(int i=0; i<vector.size(); i++)
        {
            item = new MicroItem();
            String line = (String) vector.elementAt(i);

            start   = 0;
            end     = line.indexOf(';');
            item.id  = Integer.parseInt( line.substring(start, end) );

            start   = end+1;
            end     = line.indexOf(';', start);
            item.title  = line.substring(start, end);

            start   = end+1;
            end     = line.indexOf(';', start);
            item.imageUrl  = line.substring(start, end);

            start   = end+1;
//            end     = line.indexOf(';', start);
            item.info  = line.substring(start);

//            boolean loadImage = (line.substring(end)).equals("1");
            item.silentLoadImage();
            addItem(item);
        }
    }

    public void setMarkListener(MarkListener sl) {
    }

    public void mark(int index) {
        selection[index] = true;
    }

    public void unmark(int index) {
        selection[index] = false;
    }

    public void setSelectionListener(SelectionListener sl) {
    }

}