package algorithms.search;

import java.util.ArrayList;

public abstract class AState {


    private boolean visited;
    private AState prev;
    private double weight;


    public AState() {

        this.visited = false;
        weight = 0;
        prev = null;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public AState getPrev() {
        return prev;
    }

    public void setPrev(AState prev) {
        this.prev = prev;
    }


}
