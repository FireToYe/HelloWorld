package tydic.cn.com.Thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

import tydic.cn.com.Util.SocketUtil;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ServerThread implements Runnable {
    Socket socket;
    BufferedReader br;
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
        try{

            String content = readClient();
            if (content!=null) {
                for (Socket s : SocketUtil.sockets) {
                    OutputStream os = s.getOutputStream();
                    os.write((content + "\n").getBytes("UTF-8"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readClient(){
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
