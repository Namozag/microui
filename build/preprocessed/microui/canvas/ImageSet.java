package microui.canvas;

import javax.microedition.lcdui.Image;
import microui.utils.ImageManager;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class ImageSet
{
    protected Image[] frames;
    protected  int     width;
    protected  int     height;
    
    public ImageSet(String path, int count)
    {
        setImageSet(path, count, "png");
    }

    private boolean setImageSet(String path, int count, String ext)
    {
        frames = new Image[count];
        for(int i=0; i<count; i++)
        {
            String url = path + (i+1) + "." + ext;
            System.out.println("image: " + url);
            frames[i] = ImageManager.get(url);
        }

        // set image height and width
        height    = frames[0].getHeight();
        width     = frames[0].getWidth();

        return true;
    }

    public Image getFrame(int index)
    {
        return frames[index];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
