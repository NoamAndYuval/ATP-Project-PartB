package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    Maze maze;

    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {

        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);


        Maze maze = (Maze) fromClient.readObject();

        Solution solution = Configurations.FindSolutionFromResources(maze);

        if (solution != null)
            toClient.writeObject(solution);
        else {
            ISearchingAlgorithm searchingAlgorithm = Configurations.mazeSearchingAlgorithm();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            assert searchingAlgorithm != null;
            solution = searchingAlgorithm.solve(searchableMaze);
            Configurations.WriteSolutionToResources(maze, solution);
            toClient.writeObject(solution);
        }

        toClient.flush();

        toClient.close();
        fromClient.close();


    }
}
