package algorithms.mazeGenerators;

public class Position {
    protected int x;
    protected int y;
    protected int val;
    protected boolean visited;
    protected Position prev;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.val = 1;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "{" +
                x +
                "," + y +
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


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Position getPrev() {
        return prev;
    }

    public void setPrev(Position prev) {
        this.prev = prev;
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }
}
