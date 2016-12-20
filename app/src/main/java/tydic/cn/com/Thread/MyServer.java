package tydic.cn.com.Thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MyServer {
    public static List<Socket> socketlist = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8001);
        Socket s = ss.accept();
        socketlist.add(s);
        new Thread((new ServerThread(s))).start();
    }
}
