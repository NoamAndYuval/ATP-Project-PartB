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

    public static ISearchingAlgorithm mazeSearchingAlgorithm() {
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


}
