package microui.canvas;

import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class FrameSet extends ImageSet
{
    private int     currentFrame    = 0;
    private boolean swingForward = true;

    // constructors

    public FrameSet(String path, int count)
    {
        super(path, count);
    }

    public int nextFrameIndex()
    {
        if(++currentFrame < frames.length)
            return currentFrame;
        currentFrame = 0;
        return 0;
    }

    public int previousFrameIndex()
    {
        System.out.println("# " + currentFrame);
        if(currentFrame > 0)
            return --currentFrame;
        return currentFrame = frames.length-1;
    }

    public int swingFrameIndex()
    {
        System.out.println("# " + currentFrame);
        if(currentFrame == 0 && swingForward == false)
            swingForward = true;
        else if(currentFrame == frames.length-1 && swingForward == true)
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

    public Image currentFrame()
    {
        return getFrame(currentFrame);
    }

    public Image previousFrame()
    {
        return getFrame(previousFrameIndex());
    }

}
