package tydic.cn.com.Util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/11/9.
 */
public class UDPSocketUtil {
    public static void ServerReceive(int port){
        byte[] msg = new byte[1024];
        try {
            DatagramPacket packet = new DatagramPacket(msg,msg.length);
            DatagramSocket socket = new DatagramSocket(port);
            socket.receive(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send(int port,String host,String message){
        DatagramSocket socket = null;
        DatagramPacket packet;
        byte[] msgByte = message.getBytes();
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            packet = new DatagramPacket(msgByte,msgByte.length,inetAddress,port);
            socket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
