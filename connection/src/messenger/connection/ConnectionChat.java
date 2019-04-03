package messenger.connection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class ConnectionChat {

    private final Socket socket;
    private  final Thread thread;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final ConnectionListener eventListener;


    public ConnectionChat(ConnectionListener eventListener, String ipAddress, int port) throws IOException {
        this(eventListener,new Socket(ipAddress,port));
    }

    public ConnectionChat(ConnectionListener eventListener, Socket socket) throws IOException {
        this.eventListener=eventListener;
        this.socket = socket;
        in=new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    eventListener.onConnectionReady(ConnectionChat.this);
                    while (!thread.isInterrupted()){
                   // String msg=in.readLine();
                    eventListener.onReceiveString(ConnectionChat.this ,in.readLine());
                    }
                }catch(IOException e){
                    eventListener.onException(ConnectionChat.this,e);

                }finally {
                    eventListener.onDisconnect(ConnectionChat.this);
                }
            }
        });
        thread.start();

    }
//многопоточные методы, synchronized для потокобезопасности
    public  synchronized void sendMessage(String context){
        try {
            out.write(context+"\r\n");   //символы конца строки(возврат проверки и перевод строки)
            out.flush();// очищает буфер и передает все  данные(тобеж текст сообщения)
        } catch (IOException e) {
            eventListener.onException(ConnectionChat.this,e);
            disconnect();
        }
    }


    public  synchronized void disconnect(){
        thread.isInterrupted();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(ConnectionChat.this,e);
        }
    }

    @Override
    public String toString() {
        return "Connection:"+socket.getInetAddress()+":"+socket.getPort();
    }
}
