package Serve;


import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoSever {
    public static void main(String[] args) throws Exception {
        new EchoSeverHandle();
    }
}
class EchoSeverHandle {
    private ServerSocket serverSocket;
    public EchoSeverHandle() throws Exception{
        int PORT= 6789;
        serverSocket =new ServerSocket(ServerInfo.PORT);
        System.out.println("Echo服务段的启动,该服务在"+ServerInfo.PORT+"端口上监听...");
        this.connectClient();

    }
    private void connectClient() throws Exception{

        boolean flag =true;

        while (flag){
            final Socket client = this.serverSocket.accept();//等待客户连接
            Thread thread =new Thread(()->{
                    try{
                        Scanner scan =new Scanner(client.getInputStream());
                        PrintStream out = new PrintStream(client.getOutputStream());
                        scan.useDelimiter("\n");
                        boolean clientFlag =true;
                        while (clientFlag) {
                            if (scan.hasNext()) {//当前的内容
                                String inputData = scan.next();//获得输入的数据
                                System.out.println("获取到客户端传入数据："+inputData);
                                if ("exit".equalsIgnoreCase(inputData)) {//信息结束
                                    System.out.println("[ECHO]bye bye ... kiss!");
                                }else{
                                   out.println("[ECHO]"+inputData);
                                   byte [] bytes = inputData.getBytes("UTF-8");
                                   out.write(bytes);
                                    out.flush();
                                }
//                                if (StringUtils.isEmpty(str) || "".equalsIgnoreCase(str)) {
//                                    System.out.println("");
//                                }

                            }
                        }
                        client.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });

            thread.start();//启动多线程
        }

    }
}
