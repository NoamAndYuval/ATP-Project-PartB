package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze  implements IServerStrategy{

    public ServerStrategyGenerateMaze() {

    }
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {

        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);



        MyMazeGenerator mazeGenerator = new MyMazeGenerator();
        int [] al = (int[]) fromClient.readObject();
        Maze maze = mazeGenerator.generate(al[0],al[1]);

        MyCompressorOutputStream myCompressorOutputStream = new MyCompressorOutputStream(new ByteArrayOutputStream());
        myCompressorOutputStream.write(maze.toByteArray());

        toClient.writeObject(((ByteArrayOutputStream)myCompressorOutputStream.getOut()).toByteArray());

        toClient.flush();

        toClient.close();
        fromClient.close();






    }

}
