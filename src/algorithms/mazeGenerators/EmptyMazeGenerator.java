package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator
{
    @Override
    public Maze generate(int row, int col) {
        return new Maze(row,col);
    }
}
