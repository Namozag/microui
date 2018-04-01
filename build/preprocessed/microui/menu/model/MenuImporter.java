/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.menu.model;

import java.util.Vector;

/**
 *
 * @author h
 */
public class MenuImporter {

    public static void addCsvVectorToMenu(MicroMenu menu, Vector vector)
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
//            item.silentLoadImage();
            item.loadImage();
            menu.addItem(item);
        }
    }

    public static void fillImageMenu(MicroMenu menu, String path, int count)
    {
        for(int i=0; i<count; i++)
        {
            menu.addItem(new MicroItem("", path + (i+1) + ".png", MicroItem.LOAD_IMAGE_OK));
        }
    }
}
