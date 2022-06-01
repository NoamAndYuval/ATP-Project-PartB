package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);


        Maze maze = (Maze) fromClient.readObject();
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution= bestFirstSearch.solve(searchableMaze);
        toClient.writeObject(solution);

        toClient.flush();




    }
}
