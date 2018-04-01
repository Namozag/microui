package microui.menu.model;

import microui.utils.ImageManager;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class MicroItem {
    public int      id;
    public String   title   = "";
    public String   imageUrl= "";
    public Image    image   = null;
    public String   info    = "";

    public final static int LOAD_IMAGE_NOT  = 0;
    public final static int LOAD_IMAGE_OK   = 1;
    public final static int LOAD_IMAGE_SILENT   = 2;

    public MicroItem() {
    }

    public MicroItem(String titleIn, Image imageIn) {
        title   = titleIn;
        image   = imageIn;
    }

    public MicroItem(String titleIn, String imageUrlIn, int doLoadImage) {
        title   = titleIn;
        imageUrl= imageUrlIn;

        if(doLoadImage == LOAD_IMAGE_OK)
            loadImage();
        else if(doLoadImage == LOAD_IMAGE_SILENT)
            silentLoadImage();
    }

    public MicroItem(int idIn, String titleIn, Image imageIn, String infoIn) {
        id      = idIn;
        title   = titleIn;
        image   = imageIn;
        info    = infoIn;
    }

    public MicroItem(int idIn, String titleIn, String imageUrlIn, String infoIn, int doLoadImage) {
        id      = idIn;
        title   = titleIn;
        imageUrl= imageUrlIn;
        info    = infoIn;
        if(doLoadImage == LOAD_IMAGE_OK)
            loadImage();
        else if(doLoadImage == LOAD_IMAGE_SILENT)
            silentLoadImage();
    }
    
    public synchronized void loadImage() {
        this.image = ImageManager.get(imageUrl);
    }

    public void unloadImage() {
        this.image = null;
    }

    public void silentLoadImage() {
        Thread runner = new Thread() {
            public void run() {
                loadImage();
            }
        };
        runner.start();
    }

}