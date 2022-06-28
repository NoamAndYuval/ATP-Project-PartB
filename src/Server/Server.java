package Server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService ClientExecutor ;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        this.ClientExecutor = Executors.newFixedThreadPool((Configurations.ThreadPoolSize()));
    }

    public void start() {
        new Thread(this::StartServer).start();
    }

    private void StartServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient handleClient = new handleClient(clientSocket,this.serverStrategy);
                    this.ClientExecutor.execute(handleClient);
                } catch (SocketTimeoutException e) {

                }
            }
            ClientExecutor.shutdown();
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    public void stop() {


        stop = true;

    }
}
class handleClient implements Runnable {

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    private Socket clientSocket;
    private IServerStrategy serverStrategy;

    public handleClient(Socket clientSocket, IServerStrategy serverStrategy) {
        this.clientSocket = clientSocket;
        this.serverStrategy = serverStrategy;
    }

    @Override
    public void run() {
        try {
            serverStrategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
        }


    }
}