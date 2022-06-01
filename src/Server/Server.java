package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
    }

    public void start() {
        new Thread(){
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {

                    serverSocket = new ServerSocket(port);
                    serverSocket.setSoTimeout(listeningIntervalMS);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        // This thread will handle the new Client
                        new Thread(() -> {
                            handleClient(clientSocket);
                        }).start();

                    } catch (IOException e) {

                    }
                }
            }

            }.start();

    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void stop() {
        System.out.println("Sever is stop....");
        stop = true;
    }
}
