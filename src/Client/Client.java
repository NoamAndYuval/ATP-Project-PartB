package Client;

import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    public void start() {
        try {
            Thread.sleep(130);
            Socket serverSocket = new Socket(serverIP, serverPort);
            clientStrategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void communicateWithServer() {
        start();
    }
}