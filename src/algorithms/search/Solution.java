package algorithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> stateList;

    public Solution() {
        stateList = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath() {
        return stateList;
    }

    public void addState(AState state) {
        stateList.add(0, state);
    }

    public int getSulSize() {
        return stateList.size();
    }
}
