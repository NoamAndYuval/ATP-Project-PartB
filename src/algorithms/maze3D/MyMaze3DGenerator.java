package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    @Override


    public Maze3D generate(int depth, int row, int col) {

        Maze3D maze = new Maze3D(depth, row, col);
        ArrayList<Position3D> positionQueue = new ArrayList<Position3D>();
        positionQueue.add(maze.getStartPosition());
        while (!positionQueue.isEmpty()) {
            int rd = (int) ((Math.random() * positionQueue.size()));
            Position3D t = positionQueue.get(rd);
            positionQueue.remove(rd);
            t.setVisited(true);
            int x = t.getRowIndex();
            int y = t.getColumnIndex();
            int z = t.getDepthIndex();
            maze.set_val(z, x, y, 0);
            if (t.getPrev() != null) {
                if (x != t.getPrev().getRowIndex()) {
                    if (x > t.getPrev().getRowIndex()) {
                        maze.getPosition(z, x - 1, y).setVisited(true);
                        maze.getPosition(z, x - 1, y).setVal(0);
                    } else {
                        maze.getPosition(z, x + 1, y).setVisited(true);
                        maze.getPosition(z, x + 1, y).setVal(0);
                    }
                }
                if (y != t.getPrev().getColumnIndex()) {
                    if (y > t.getPrev().getColumnIndex()) {
                        maze.getPosition(z, x, y - 1).setVisited(true);
                        maze.getPosition(z, x, y - 1).setVal(0);
                    } else {
                        maze.getPosition(z, x, y + 1).setVisited(true);
                        maze.getPosition(z, x, y + 1).setVal(0);
                    }
                }
                if (z != t.getPrev().getDepthIndex()) {
                    if (z > t.getPrev().getDepthIndex()) {
                        maze.getPosition(z - 1, x, y).setVisited(true);
                        maze.getPosition(z - 1, x, y).setVal(0);
                    } else {
                        maze.getPosition(z + 1, x, y).setVisited(true);
                        maze.getPosition(z + 1, x, y).setVal(0);
                    }

                }
            }
            ArrayList<Position3D> tempArr = maze.getNeighborhood(t, 2);
            for (Position3D p : tempArr) {
                if (!p.isVisited())
                    positionQueue.add(p);
                else
                    p.setPrev(null);
            }
        }
        maze.getPosition(depth - 1, row - 2, col - 1).setVal(0);
        maze.getPosition(depth - 1, row - 1, col - 2).setVal(0);
        maze.getPosition(depth - 1, row - 1, col - 1).setVal(0);
        maze.getPosition(depth - 1, row - 2, col - 2).setVal(0);
        return maze;
    }



}

