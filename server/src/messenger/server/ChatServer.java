package messenger.server;

import messenger.connection.ConnectionChat;
import messenger.connection.ConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer  implements ConnectionListener {

    public static void main(String[] args) {
        new ChatServer();
    }

//список соединений
    private  final ArrayList<ConnectionChat> connections=new ArrayList<>();
    private ChatServer() {

        System.out.println("Server running...");
        try(ServerSocket serverSocket=new ServerSocket(8080)){
            while (true){
                try{
                new ConnectionChat(this,serverSocket.accept());
                }catch (IOException e){
                    System.out.println("ConnectionCat Exception:"+e);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);   //если что то непредвиденное то роняем приложение
        }
    }

    @Override
    public synchronized void onConnectionReady(ConnectionChat connection) {                 //чтобы нельзя было одновременно из разных потоков в них попасть

        connections.add(connection);
        sendToAllConnections("Client connected:"+connection);
    }

    @Override
    public synchronized void onReceiveString(ConnectionChat connection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(ConnectionChat connection) {
        connections.remove(connection);
        sendToAllConnections("Client disconnected:"+connection);
    }

    @Override
    public synchronized void onException(ConnectionChat connection, Exception e) {
        System.out.println("TCP Connection Exception:"+e);
    }

    private void sendToAllConnections(String context){
        System.out.println(context);
        final int cnt=connections.size();
        for (int i = 0; i <cnt ; i++) {
            connections.get(i).sendMessage(context);
        }

    }
}
