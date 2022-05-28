package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class Maze3D extends Maze {

    protected int depth;
    private Position3D[][][] maze_matrix;

    public Maze3D(int row, int col, int depth) {
        super(row, col);
        this.depth = depth;
        maze_matrix = new Position3D[this.depth][this.row][this.col];
        for (int k = 0; k < this.depth; k++) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    maze_matrix[k][i][j] = new Position3D(k, i, j);
                }
            }
        }
        source = maze_matrix[0][0][0];
        target = maze_matrix[this.depth - 1][this.row - 1][this.col - 1];
    }


    @Override
    public void print() {

        for (int k = 0; k < this.depth; k++) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    Position3D temp = maze_matrix[k][i][j];
                    if (temp == source)
                        System.out.print("S");
                    else if (temp == target)
                        System.out.print("E");
                    else
                        System.out.print(temp.getVal());
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }

    }

    public void set_val(int depth, int row, int col, int val) {
        if (depth > this.depth || row >= this.row || col >= this.col || depth < 0 || row < 0 || col < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (val != 0 && val != 1) {
            throw new IllegalArgumentException();
        }
        maze_matrix[depth][row][col].setVal(val);
    }


    public ArrayList<Position3D> getNeighborhood(Position3D pos, int dis) {
        ArrayList<Position3D> positionArrayList = new ArrayList<>();
        int x = pos.getRowIndex();
        int y = pos.getColumnIndex();
        int z = pos.getDepthIndex();
        if (x + dis < row) {
            positionArrayList.add(this.maze_matrix[z][x + dis][y]);
            this.maze_matrix[z][x + dis][y].setPrev(pos);
        }
        if (y + dis < col) {
            positionArrayList.add(this.maze_matrix[z][x][y + dis]);
            this.maze_matrix[z][x][y + dis].setPrev(pos);
        }
        if (x - dis >= 0) {
            positionArrayList.add(this.maze_matrix[z][x - dis][y]);
            this.maze_matrix[z][x - dis][y].setPrev(pos);

        }
        if (y - dis >= 0) {
            positionArrayList.add(this.maze_matrix[z][x][y - dis]);
            this.maze_matrix[z][x][y - dis].setPrev(pos);
        }
        if (z + dis < depth) {
            positionArrayList.add(this.maze_matrix[z + dis][x][y]);
            this.maze_matrix[z + dis][x][y].setPrev(pos);
        }
        if (z - dis >= 0) {
            positionArrayList.add(this.maze_matrix[z - dis][x][y]);
            this.maze_matrix[z - dis][x][y].setPrev(pos);

        }
        return positionArrayList;

    }

    public Position3D getPosition(int z, int x, int y) {
        try {
            return maze_matrix[z][x][y];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Position3D getStartPosition() {
        return (Position3D) this.source;
    }

    @Override
    public Position3D getGoalPosition() {
        return (Position3D) this.target;
    }

    public int getDepth() {
        return depth;
    }

}






