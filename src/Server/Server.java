package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService ClientExecutor;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.ClientExecutor = Executors.newFixedThreadPool(Configurations.ThreadPoolSize());
    }


    public void start() {
        new Thread(this::StartServer).start();
    }

    private void StartServer() {
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
                System.out.println("New Client arrived");
                handleClient handlerClient = new handleClient(clientSocket, strategy);
                ClientExecutor.execute(handlerClient);


            } catch (IOException e) {


            }
        }

    }

    public void stop() {
        System.out.println("Sever is stop....");
        ClientExecutor.shutdown();
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
        System.out.println("Client:"+clientSocket.toString()+" is running");
        try {
            serverStrategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
        }


    }
}
