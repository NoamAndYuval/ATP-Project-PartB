package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        Maze maze = new Maze(row, col);
        ArrayList<Position> positionQueue = new ArrayList<Position>();
        positionQueue.add(maze.getStartPosition());
        Position last = maze.getStartPosition();
        while (last != maze.getGoalPosition()) {
            int rd = (int) ((Math.random() * positionQueue.size()));
            Position t = positionQueue.get(rd);
            t.setVisited(true);
            t.setVal(0);
            positionQueue = maze.getNeighborhood(t, 1);
            ArrayList<Position> temp = new ArrayList<Position>();
            for (Position p : positionQueue) {
                if (p.getRowIndex() > t.getRowIndex() || p.getColumnIndex() > t.getColumnIndex()) {
                    temp.add(p);
                }
            }
            positionQueue = temp;
            last = t;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(!maze.getPosition(i,j).isVisited()){
                    maze.getPosition(i,j).setVal((int) (Math.random()*2));
                }
            }
        }
        maze.Copy();
        return maze;
    }
}
