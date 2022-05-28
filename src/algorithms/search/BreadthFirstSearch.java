package algorithms.search;

import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    @Override
    public Solution solve(ISearchable Search) {
        return BFSsolve(Search, new LinkedList<>());

    }

    protected Solution BFSsolve(ISearchable Search, Queue<AState> queue) {
        Search.resetAState();
        queue.add(Search.getFirst());
        while (!queue.isEmpty()) {
            AState temp = queue.remove();
            this.NumberOfNodesEvaluated++;
            temp.setVisited(true);
            if (temp == Search.getTarget())
                return reconstructionPath(Search.getTarget());
            for (AState neighbor : Search.getAllPossibleStates(temp)) {

                if (!neighbor.isVisited() && neighbor.getPrev() == null) {
                    neighbor.setPrev(temp);
                    queue.add(neighbor);
                }
            }
        }
        return null;

    }


    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
