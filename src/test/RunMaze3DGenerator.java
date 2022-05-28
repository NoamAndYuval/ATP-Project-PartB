package test;

import algorithms.maze3D.*;


public class RunMaze3DGenerator {

    public static void main(String[] args) {

        testMazeGenerator(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {

// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100, 100, 100)));
// generate another maze
        Maze3D maze = mazeGenerator.generate(6/*rows*/, 6, 6/*columns*/);
// prints the maze

        maze.print();
//        System.out.println("**********************");
// get the maze entrance
        // Position startPosition = maze.getStartPosition();
// print the start position
        //System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        //System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

}

