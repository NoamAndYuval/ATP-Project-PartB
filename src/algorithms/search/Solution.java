package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution  implements Serializable {

    private ArrayList<AState> stateList;

    public Solution() {
        stateList = new ArrayList<AState>();
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
