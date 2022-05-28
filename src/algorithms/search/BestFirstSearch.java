package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch {

    @Override
    public Solution solve(ISearchable Search) {
        Queue<AState> priorityQueue = new PriorityQueue<>(new AStateComparator());
        return BFSsolve(Search, priorityQueue);
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}

class AStateComparator implements Comparator<AState> {

    @Override
    public int compare(AState o1, AState o2) {
        if (o1.getWeight() > o2.getWeight())
            return -1;
        else if (o1.getWeight() < o2.getWeight())
            return 1;
        return 0;
    }
}
