package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int row, int col) {

        if (row<=0||col<=0){
            return null;
        }
        Maze maze = new Maze(row, col);
        if (row<2||col<2){
            for (int i=0;i<row;i++){
                for (int j=0;j<col;j++){
                    maze.set_val(i,j,0);
                }
            }
            return maze;
        }
        ArrayList<Position> positionQueue = new ArrayList<Position>();
        positionQueue.add(maze.getStartPosition());
        while (!positionQueue.isEmpty()) {
            int rd = (int) ((Math.random() * positionQueue.size()));
            Position t = positionQueue.get(rd);
            positionQueue.remove(rd);
            t.setVisited(true);
            int x = t.getRowIndex();
            int y = t.getColumnIndex();
            maze.set_val(x, y, 0);
            if (t.getPrev() != null) {
                if (x != t.getPrev().getRowIndex()) {
                    if (x > t.getPrev().getRowIndex()) {
                        maze.getPosition(x - 1, y).setVisited(true);
                        maze.getPosition(x - 1, y).setVal(0);
                    } else {
                        maze.getPosition(x + 1, y).setVisited(true);
                        maze.getPosition(x + 1, y).setVal(0);
                    }
                }
                if (y != t.getPrev().getColumnIndex()) {
                    if (y > t.getPrev().getColumnIndex()) {
                        maze.getPosition(x, y - 1).setVisited(true);
                        maze.getPosition(x, y - 1).setVal(0);
                    } else {
                        maze.getPosition(x, y + 1).setVisited(true);
                        maze.getPosition(x, y + 1).setVal(0);
                    }
                }
            }
            ArrayList<Position> tempArr = maze.getNeighborhood(t, 2);
            for (Position p : tempArr) {
                if (!p.isVisited())
                    positionQueue.add(p);
                else
                    p.setPrev(null);
            }
        }
        maze.ChooseTargetAndCopy();
        return maze;
    }


}
