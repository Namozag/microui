package microui.canvas;


import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class Painter
{
    public static final int origin = Graphics.LEFT|Graphics.TOP;
    public  int width, height;
    public Image   outputImage; Graphics g;

    public Painter()
    {
        
    }

    public Painter(int w, int h)
    {
        this.width  = w;    this.height = h;

        outputImage = Image.createImage(w, h);
        g           = outputImage.getGraphics();
    }

    public void fillBlack()
    {
        g.setColor(0x000000);
        g.fillRect(0, 0, width, height);
    }

    public void fillWhite()
    {
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, width, height);
    }

    public void setG(Graphics gIn)
    {
        this.g = gIn;
    }

    public void inserImageIntoLocation(Image im, int x, int y)
    {
        g.drawImage(im, x, y, Graphics.TOP|Graphics.LEFT);
    }

    public Image getImage(String url)
    {
        Image im ;
        try {
            im = Image.createImage(url);
        } catch (IOException ex) {
            System.out.println("no image : " + url);
//            ex.printStackTrace();
            return null;
        }

        return im;
    }

    public boolean insertImageWithAlign(String url, int h, int v)
    {
        insertImageWithAlign(getImage(url), h, v);
        return true;
    }
    public void insertImageWithAlign(Image im, int h, int v)
    {
        int x=0, y=0;

        switch(v)
        {
            case Graphics.TOP       : y = 0;        break;
            case Graphics.VCENTER   : y = height/2; break;
            case Graphics.BOTTOM    : y = height;   break;
        }

        switch(h)
        {
            case Graphics.LEFT      : x = 0;        break;
            case Graphics.HCENTER   : x = width/2;  break;
            case Graphics.RIGHT     : x = width;    break;
        }

        g.drawImage(im, x, y, v|h );

    }

    public boolean insertTiles(String url, int position)
    {
        return insertTiles(url, position, 0);
    }

    public boolean insertTiles(String url, int position, int space)
    {
        insertTiles(getImage(url), position);
        return true;
    }

    public void insertTiles(Image im, int position)
    {
        insertTiles(im, position, 0);
    }

    public boolean insertTilesInto(String url, int position,int shift, int space)
    {
        insertTilesInto(getImage(url), position, shift, space);
        return true;
    }
    public void insertTilesInto(Image im, int position,int shift, int space)
    {
        int imax = 0;
        int x = 0, y = 0, v = 0, h = 0;
        boolean increasex = false, increasey = false;
        int imw, imh;
        imw = im.getWidth();    imh = im.getHeight();

        switch(position)
        {
            case Graphics.TOP   : y = shift;    imax = width/(imw+space) +1;    increasex = true;   break;
            case Graphics.LEFT  : x = shift;    imax = height/(imh+space) +1;   increasey = true;   break;
        }

        for(int i=0; i<imax; i++)
        {
            g.drawImage(im, x, y, v|h );
            if(increasex)
                x += imw + space;
            if(increasey)
                y += imh + space;
        }
    }

    public void insertBgTiles(Image im, int space)
    {
        int position = 0;
        int positionShift = im.getHeight();
        while(position < height+positionShift)
        {
            insertTilesInto(im, Graphics.TOP, position, 0);
            position += positionShift;
        }
        //insertTilesInto(Image im, int position,int shift, int space)
    }

    public void insertTiles(Image im, int position, int space)
    {
        int imax = 0;
        int x = 0, y = 0, v = 0, h = 0;
        boolean increasex = false, increasey = false;
        int imw, imh;
        imw = im.getWidth();    imh = im.getHeight();

        switch(position)
        {
            case Graphics.TOP   : v = Graphics.TOP;     h = Graphics.LEFT;  x = 0; y = 0;       imax = width/(imw+space) +1;   increasex = true;    break;
            case Graphics.BOTTOM: v = Graphics.BOTTOM;  h = Graphics.LEFT;  x = 0; y = height;  imax = width/(imw+space) +1;   increasex = true;    break;
            case Graphics.LEFT  : v = Graphics.TOP;     h = Graphics.LEFT;  x = 0; y = 0;       imax = height/(imh+space) +1;  increasey = true;    break;
            case Graphics.RIGHT : v = Graphics.TOP;     h = Graphics.RIGHT; x = width;  y = 0;  imax = height/(imh+space) +1;  increasey = true;    break;
        }

        for(int i=0; i<imax; i++)
        {
            g.drawImage(im, x, y, v|h );
            if(increasex)
                x += imw + space;
            if(increasey)
                y += imh + space;
        }
    }

    public void insertBorder(Image top, Image bottom, Image strip)
    {
        int topHeight       = top.getHeight();
        int bottomHeight    = bottom.getHeight();
        int stripHeight     = strip.getHeight();
        int stripWidth      = top.getWidth();

        int stripTotalHeight= height - (topHeight+bottomHeight);

        Painter stripIm = new Painter(stripWidth, stripTotalHeight);
        stripIm.setG(g);
        stripIm.insertTiles(strip, Graphics.LEFT);
    }

}
