package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {


    @Override
    public Solution solve(ISearchable Search) {
        Search.resetAState();
        Stack<AState> stateStack = new Stack<>();
        stateStack.push(Search.getFirst());
        while (!stateStack.isEmpty()) {
            AState temp = stateStack.pop();
            if(temp == Search.getTarget())
                return reconstructionPath(Search.getTarget());
            this.NumberOfNodesEvaluated++;
            temp.setVisited(true);
            for (AState neighbor : Search.getAllPossibleStates(temp)) {
                if (!neighbor.isVisited()) {
                    neighbor.setPrev(temp);
                    stateStack.push(neighbor);
                }
            }
        }
        return null;


    }


    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
