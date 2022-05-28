package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public interface IMaze3DGenerator {

    Maze3D generate(int depth, int row, int col);

    long measureAlgorithmTimeMillis(int depth, int row, int col);
}
