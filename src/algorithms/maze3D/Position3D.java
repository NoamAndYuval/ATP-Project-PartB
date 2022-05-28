package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position {
    protected int z;


    public Position3D(int z,int x, int y) {
        super(x, y);
        this.z = z;
    }


    @Override
    public String toString() {
        return "{" + x +
                "," + y +
                "," + z +
                '}';
    }

    public int getRowIndex() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getColumnIndex() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDepthIndex() { return z; }

    public void setDepthIndex(int z) { this.z = z; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Position3D getPrev() {
        return (Position3D)prev;
    }

    public void setPrev(Position3D prev) {
        this.prev = prev;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }
}
