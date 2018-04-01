package microui.utils;


import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class SMSSender
{
    private static void connect(String num, String msgToSend)
    {
        try {
            MessageConnection clientConn = (MessageConnection) Connector.open(
                    "sms://" + num );
            TextMessage tmsg =
                    (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
            tmsg.setAddress("sms://" + num );
            tmsg.setPayloadText(msgToSend);
            clientConn.send(tmsg);
        } catch (Exception ex) {
        }
    }

    public static void send(final String mobile, final String sms)
    {
        Thread runner = new Thread() {

            public void run() {
                // thread action goes here
                System.out.println("SMS to send is:" + sms);
                connect(mobile, sms);
            }
        };
        runner.start();
    }
}
