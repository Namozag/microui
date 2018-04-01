/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.generic;

import microui.*;
import javax.microedition.lcdui.Font;

/**
 *
 * @author h
 */
public class Style {

    public int colorBG = 0xFFFFCC;
    public int colorBGSELECTED = 0xFFFF00;
    public int colorBGPointed = 0xCCFF66;
    public int colorText = 0x3366CC;
    public int colorTextSELECTED = 0x336600;
    public int colorTextPointed = 0x0000CC;
    public int colorSelector = 0x003399;
    public int[] colorLevelColor = {0xCCFFCC, 0xCCFFFF, 0xCCFF99, 0xFFCCCC};

    public Font font = Font.getDefaultFont();
    public Font fontTitle = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

}
