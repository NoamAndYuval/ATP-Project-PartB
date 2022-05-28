package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @org.junit.jupiter.api.Test
    void solve() {

        IMazeGenerator mg = new MyMazeGenerator();

        for (int i = 0; i < 10; i++) {
            Maze maze = mg.generate(100 + i * i, 91 + i * i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);

            BestFirstSearch bestFirstSearch = new BestFirstSearch();
            BreadthFirstSearch breadthFirstSearch = new BestFirstSearch();
            DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

            int sizeBestsul = bestFirstSearch.solve(searchableMaze).getSulSize();
            int evaluateBest = bestFirstSearch.NumberOfNodesEvaluated;
            int sizeBFSsul = breadthFirstSearch.solve(searchableMaze).getSulSize();
            int evaluateBreadth = breadthFirstSearch.NumberOfNodesEvaluated;
            int sizeDFSsul = depthFirstSearch.solve(searchableMaze).getSulSize();


            assertFalse(evaluateBreadth > evaluateBest);
            assertFalse(sizeDFSsul < sizeBestsul || sizeBestsul != sizeBFSsul);
        }



    }

    @org.junit.jupiter.api.Test
    void getName() {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        assertEquals("BestFirstSearch", bestFirstSearch.getName());
    }
}