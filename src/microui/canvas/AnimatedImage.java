package microui.canvas;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class AnimatedImage
{
    public  final   static  int MODE_FOR    = 1;
    public  final   static  int MODE_BACK   = 2;
    public  final   static  int MODE_SWING  = 3;
    private Vector  frames  = new Vector();
    private int     currentFrame    = 0;
    private int     delay   = 100;
    public  int     imWidth;
    public  int     imHeight;
    private boolean swingForward = true;
    private int     mode = 0;

    // constructors

    public AnimatedImage()
    {
        
    }

    public AnimatedImage(String path, int count)
    {
        addImageSet(path, count, "png");
    }

    public AnimatedImage(String path, int count, String ext)
    {
        addImageSet(path, count, ext);
    }

    public boolean addImage(Image im)
    {
        frames.addElement(im);
        return true;
    }

    public boolean addImage(String url)
    {
        Image im = null;
        try {
            im = Image.createImage(url);
        } catch (IOException ex) {
            return false;
        }
        if(im != null)
        {
            return addImage(im);
        }
        return false;
    }

    public boolean addImageSet(String path, int count, String ext)
    {
        for(int i=1; i<count; i++)
        {
            addImage(path + i + "." + ext);
        }

        // set image height and width
        imHeight    = ( (Image) frames.elementAt(0) ).getHeight();
        imWidth     = ( (Image) frames.elementAt(0) ).getWidth();

        return true;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public boolean reset()
    {
        frames.removeAllElements();
        return true;
    }

    public Image getFrame(int index)
    {
        return (Image) frames.elementAt(index);
    }

    public int nextFrameIndex()
    {
        if(++currentFrame < frames.size())
            return currentFrame;
        currentFrame = 0;
        return 0;
    }

    public int previousFrameIndex()
    {
        System.out.println("# " + currentFrame);
        if(currentFrame > 0)
            return --currentFrame;
        return currentFrame = frames.size()-1;
    }

    public int swingFrameIndex()
    {
        System.out.println("# " + currentFrame);
        if(currentFrame == 0 && swingForward == false)
            swingForward = true;
        else if(currentFrame == frames.size()-1 && swingForward == true)
            swingForward = false;
        else if(swingForward)
            ++currentFrame;
        else
            --currentFrame;

        return currentFrame;
    }

    public Image swingFrame()
    {
        return getFrame(swingFrameIndex());
    }

    public Image nextFrame()
    {
        return getFrame(nextFrameIndex());
    }

    public Image getCurrentFrame()
    {
        return getFrame(currentFrame);
    }

    public Image previousFrame()
    {
        return getFrame(previousFrameIndex());
    }

    public Image animate()
    {
        if(mode == MODE_FOR)
            return nextFrame();
        else if (mode == MODE_BACK)
            return previousFrame();
        else
            return swingFrame();
    }

    public void startAnimation(Displayable d, Graphics g)
    {
        Thread t = new Thread()
        {
            public void run()
            {

            }
        }; t.start();
    }

    public void animateInCanvas(Canvas c, Graphics g)
    {
        c.repaint();
    }

    public void setTimer()
    {
        task    = new AnimationTask();
        timer   = new Timer();
    }

    public void startAnimation(int after, int delay)
    {
        setTimer();
        timer.schedule(task, after, delay);
    }

    Timer timer;
    AnimationTask task;
    class AnimationTask extends TimerTask
    {
        public AnimationTask()
        {

        }

        public void run()
        {
            if(mode == MODE_FOR)
                nextFrameIndex();
            else if(mode == MODE_BACK)
                previousFrameIndex();
            else if(mode == MODE_SWING)
                swingFrameIndex();
        }
    }

}
