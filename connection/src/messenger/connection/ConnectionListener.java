package messenger.connection;

public interface ConnectionListener {

    void onConnectionReady(ConnectionChat connection);  //Соединение установлено
    void onReceiveString(ConnectionChat connection, String value); //Соединение приняло строчку входящую
    void onDisconnect(ConnectionChat connection); // соединение прервалось
    void onException(ConnectionChat connection, Exception e); //исключение

}
