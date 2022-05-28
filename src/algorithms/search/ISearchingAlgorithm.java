package algorithms.search;

public interface ISearchingAlgorithm {

    Solution solve (ISearchable Search);
    String getName();
    int getNumberOfNodesEvaluated();

}
