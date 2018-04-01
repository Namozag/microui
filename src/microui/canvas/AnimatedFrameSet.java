package microui.canvas;

import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class AnimatedFrameSet extends FrameSet
{
    public  final   static  int MODE_FORE    = 1;
    public  final   static  int MODE_BACK   = 2;
    public  final   static  int MODE_SWING  = 3;
    private int     mode = 0;

    public AnimatedFrameSet(String path, int count, int mode)
    {
        super(path, count);
        setMode(mode);
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public Image animate()
    {
        if(mode == MODE_FORE)
            return nextFrame();
        else if (mode == MODE_BACK)
            return previousFrame();
        else
            return swingFrame();
    }

    public void animateInCanvas(Canvas c, Graphics g)
    {
        c.repaint();
    }

    public void startAnimation(int after, int delay)
    {
        task    = new AnimationTask();
        timer   = new Timer();
        timer.schedule(task, after, delay);
    }

    Timer timer;
    AnimationTask task;
    class AnimationTask extends TimerTask
    {
        public void run()
        {
            if(mode == MODE_FORE)
                nextFrameIndex();
            else if(mode == MODE_BACK)
                previousFrameIndex();
            else if(mode == MODE_SWING)
                swingFrameIndex();
        }
    }

}
