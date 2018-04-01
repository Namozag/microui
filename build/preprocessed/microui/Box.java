/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui;

/**
 *
 * @author hany ahmed said : hanylink@gmail.com
 */
public class Box {
    public int x;
    public int y;
    public int w;
    public int h;

    public Box() {
    }

    public Box(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
