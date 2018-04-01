package microui.utils;


import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class ImageSlicer
{
    private Image image;
    private String url;
    private int count;
    private int height;
    private int width;
    private int sliceWidth;
    int ORIGIN = Graphics.LEFT | Graphics.TOP;

    public ImageSlicer(String url, int count){
        this.url = url;
        this.count = count;

        this.image = ImageManager.get(url);
        height = image.getHeight();
        width   = image.getWidth();
        sliceWidth = width / count;

        System.out.println(
                "\nurl\t"       + url +
                "\ncount\t"     + count +
                "\nheight\t"    + height +
                "\nwidth\t"     + width +
                "\nswidth\t"    + sliceWidth
                );
    }

    public Image getSlice(int index){
        Image slice = Image.createImage(image, sliceWidth * index, 0, sliceWidth, height, Sprite.TRANS_NONE);
        return slice;
    }

    public Image getSliceSet(int[] indecs){
        Image slice = Image.createImage(sliceWidth*indecs.length, height);
        Graphics g = slice.getGraphics();
        for(int i=0; i<indecs.length; i++){
            Image islice = getSlice(indecs[i]);
            g.drawImage(islice, sliceWidth * i, 0, ORIGIN);
        }
        return slice;
    }

    public Image getSliceSet(String indecesString){
        int[] indeces = new int[indecesString.length()];
        for(int i=0; i<indecesString.length(); i++){
            indeces[i] = Integer.parseInt( String.valueOf( indecesString.charAt(i) ) );
        }
        return getSliceSet(indeces);
    }


}
