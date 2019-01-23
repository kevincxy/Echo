package Client;

import Serve.ServerInfo;
import Util.InputUtils;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient implements AutoCloseable {

    private Socket client;
    //监听服务器
    public EchoClient() throws Exception{
        this.client = new Socket(ServerInfo.SERVER_HOST,ServerInfo.PORT);
        System.out.println("已经开始监听服务器,可以发送请求了...");
        this.accient();
    }
    //服务器客户端交互操作
    public void accient() throws Exception{
        Scanner scan = new Scanner(this.client.getInputStream());//服务器端的输出即为客户端的输入
        PrintStream out = new PrintStream(this.client.getOutputStream());//向服务器发送信息
        scan.useDelimiter("\n");
        boolean flag = true;
        while(flag) {
            String data = InputUtils.getstring("请说出你的梦想：");
            out.println(data);
            byte [] bytes = data.getBytes("UTF-8");
            out.write(bytes);
            out.flush();
            if("exit".equalsIgnoreCase(data)){
                flag = false;
            }
            if(scan.hasNext()){
                //得用nextLine
                String result = scan.nextLine();
                byte[] bytes1 = result.getBytes("UTF-8");
                System.out.println("服务端的返回："+new String(bytes1));
            }
        }
    }
    @Override
    public void close() throws Exception{
        this.client.close();
    }
    public static void main(String[] args){
        try(EchoClient echoClient = new EchoClient()){

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
