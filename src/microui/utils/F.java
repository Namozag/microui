package microui.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class F
{
    static int stepIndex = 0;
    public static void echo(String s)
    {
        System.out.print("\n" + s);
    }

    public static void append(String s)
    {
        System.out.print("\t| " + s);
    }

    public static void start(String s)
    {
        stepIndex = 0;
        System.out.print("\n" + s + "\t: " + stepIndex++);
    }
    public static void step()
    {
        System.out.print(" - " + stepIndex++);
    }

    public static void error(String message, Exception ex)
    {
        System.out.println("ERROR\t" + message + "\t\t" + ex.getMessage());
    }
}
