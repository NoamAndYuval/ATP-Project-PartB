package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    ArrayList<AState> getAllPossibleStates(AState state);
    AState getFirst();
    AState getTarget();
    void resetAState();
}
