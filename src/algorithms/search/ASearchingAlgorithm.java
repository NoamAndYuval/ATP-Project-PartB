package algorithms.search;

import algorithms.mazeGenerators.Maze;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int NumberOfNodesEvaluated = 0;


    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
    public Solution reconstructionPath(AState last) {
        Solution solution = new Solution();
        while (last != null) {
            solution.addState(last);
            last = last.getPrev();
        }
        return solution;
    }
}
