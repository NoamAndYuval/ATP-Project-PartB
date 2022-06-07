package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    Maze maze;

    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException, ClassNotFoundException {

        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);


        Maze maze = (Maze) fromClient.readObject();

        Solution solution = FindSolutionFromResources(maze);

        if (solution != null)
            toClient.writeObject(solution);
        else {
            ISearchingAlgorithm searchingAlgorithm = Configurations.mazeSearchingAlgorithm();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            assert searchingAlgorithm != null;
            solution = searchingAlgorithm.solve(searchableMaze);
            WriteSolutionToResources(maze, solution);
            toClient.writeObject(solution);
        }

        toClient.flush();

        toClient.close();
        fromClient.close();


    }

    private static synchronized Solution FindSolutionFromResources(Maze maze) throws IOException, ClassNotFoundException {

        byte[] mazeBytes = maze.toByteArray();
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");

        File dir = new File(tempDirectoryPath + "/MazeAndSolution");
        if (!dir.exists())
            return null;
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File MazeDir : directoryListing) {
                // Do something with child
                File[] MazeDirList = MazeDir.listFiles();
                if (MazeDirList == null)
                    continue;
                File MazeFile = MazeDirList[1];
                File SulFile = MazeDirList[0];

                byte[] lst = Files.readAllBytes(MazeFile.toPath());
                if (Arrays.equals(lst, mazeBytes)) {

                    FileInputStream fileInputStream = new FileInputStream(SulFile);
                    ObjectInputStream fromSulFile = new ObjectInputStream(fileInputStream);
                    Solution solution = (Solution) fromSulFile.readObject();
                    fromSulFile.close();
                    return solution;
                }
            }
        }
        return null;
    }

    private static synchronized void WriteSolutionToResources(Maze maze, Solution solution) {

        try {
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            byte[] mazeBytes = maze.toByteArray();
            File dir = new File(tempDirectoryPath + "/MazeAndSolution");
            if (!dir.exists())
                dir.mkdir();

            File[] directoryListing = dir.listFiles();
            File directory = new File(tempDirectoryPath + "/MazeAndSolution/Maze" + directoryListing.length);
            if (!directory.exists()) {
                directory.mkdir();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }
            File MazeFile = new File(directory.getPath() + "/Maze");
            File SolFile = new File(directory.getPath() + "/Sol");
            MazeFile.getParentFile().mkdirs();
            MazeFile.createNewFile();
            SolFile.getParentFile().mkdirs();
            SolFile.createNewFile();

            FileOutputStream MazeOutputStream = new FileOutputStream(MazeFile);

            FileOutputStream SolOutputStream = new FileOutputStream(SolFile);
            ObjectOutputStream SolToFile = new ObjectOutputStream(SolOutputStream);


            MazeOutputStream.write(mazeBytes);
            SolToFile.writeObject(solution);

            MazeOutputStream.flush();
            SolToFile.flush();

            MazeOutputStream.close();
            SolToFile.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
