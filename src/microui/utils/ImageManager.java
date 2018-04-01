package microui.utils;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ContentConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class ImageManager
{
    public static Image get(String url)
    {
        System.out.println("get image: " + url);
        try {
            if(url.indexOf(":") != -1)
            {
                ContentConnection connection = (ContentConnection) Connector.open(url);
                InputStream inStream = connection.openInputStream();
                return Image.createImage(inStream);
            }
            else{
            return Image.createImage(url);
            }
        } catch (IOException ex) {
            System.out.println("exception - no image");
        }
        return null;
    }


    public static Image resize(Image image, int width, int height)
    {
       int sourceWidth = image.getWidth();
       int sourceHeight = image.getHeight();
       int thumbWidth = width;
       int thumbHeight = height;

       if (thumbHeight == -1)
          thumbHeight = thumbWidth * sourceHeight / sourceWidth;

       Image thumb = Image.createImage(thumbWidth, thumbHeight);
       Graphics g = thumb.getGraphics();

       for (int y = 0; y < thumbHeight; y++)
       {
          for (int x = 0; x < thumbWidth; x++)
          {
            g.setClip(x, y, 1, 1);
            int dx = x * sourceWidth / thumbWidth;
            int dy = y * sourceHeight / thumbHeight;
            g.drawImage(image, x - dx, y - dy, Graphics.LEFT | Graphics.TOP);
          }
       }

       Image immutableThumb = Image.createImage(thumb);

       return immutableThumb;
    }

    public static Image rotate(Image image, int r)
    {
        int[]dirs = {0, 5, 3, 6};
        // 0 - 5 -3 -6
        return Image.createImage(image, 0, 0, image.getWidth(), image.getHeight(), dirs[r]);
    }
}
