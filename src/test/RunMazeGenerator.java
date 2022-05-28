package test;
import algorithms.mazeGenerators.*;

import java.io.IOException;

public class RunMazeGenerator {
    public static void main(String[] args) throws IOException {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) throws IOException {

// prints the time it takes the algorithm to run
 System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/)));
// generate another maze
       Maze maze = mazeGenerator.generate(100/*rows*/, 50/*columns*/);
// prints the maze

        maze.print();
//        maze.Display();
        System.out.println("**********************");
// get the maze entrance
        Position startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

}
