package tydic.cn.com.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import tydic.cn.com.Thread.ServerThread;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SocketUtil {
    public static ArrayList<Socket> sockets = new ArrayList<Socket>();

    public static void main(String args[]){
        try {
            ServerSocket serverSocket = new ServerSocket(8010);
            Socket socket = serverSocket.accept();
            sockets.add(socket);
            new Thread(new ServerThread(socket)).start();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
