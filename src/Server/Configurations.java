package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Properties;

public class Configurations {

    public static int ThreadPoolSize() {
        try {
            InputStream input = new FileInputStream("resources/config.properties");
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            return Integer.parseInt(prop.getProperty("threadPoolSize"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    public static IMazeGenerator mazeGeneratingAlgorithm() {
        try {
            InputStream input = new FileInputStream("resources/config.properties");
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            String mazeGen = prop.getProperty("mazeGeneratingAlgorithm");
            if (mazeGen.equals("MyMazeGenerator"))
                return new MyMazeGenerator();
            if (mazeGen.equals("SimpleMazeGenerator"))
                return new SimpleMazeGenerator();
            if (mazeGen.equals("EmptyMazeGenerator"))
                return new EmptyMazeGenerator();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public static  ISearchingAlgorithm mazeSearchingAlgorithm() {
        try {
            InputStream input = new FileInputStream("resources/config.properties");
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            String mazeGen = prop.getProperty("mazeSearchingAlgorithm");
            if (mazeGen.equals("BestFirstSearch"))
                return new BestFirstSearch();
            if (mazeGen.equals("BreadthFirstSearch"))
                return new BreadthFirstSearch();
            if (mazeGen.equals("DepthFirstSearch"))
                return new DepthFirstSearch();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public static synchronized Solution FindSolutionFromResources(Maze maze) throws IOException, ClassNotFoundException {

        byte[] mazeBytes = maze.toByteArray();

        File dir = new File("resources/MazeAndSolution");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File MazeDir : directoryListing) {
                // Do something with child
                File[] MazeDirList = MazeDir.listFiles();
                if (MazeDirList==null)
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

    public static synchronized void WriteSolutionToResources(Maze maze ,Solution solution){

        try {
            byte[] mazeBytes = maze.toByteArray();
            File dir = new File("resources/MazeAndSolution");
            File[] directoryListing = dir.listFiles();
            File directory = new File("resources/MazeAndSolution/Maze" + directoryListing.length);
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


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }





    }

}
