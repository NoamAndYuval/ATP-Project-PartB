package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    public static double WEIGHT = 2;
    protected Maze maze;
    private MazeState[][] AllStates;
    protected AState source;
    protected AState target;

    public SearchableMaze(Maze maze) {
        AllStates = new MazeState[maze.getRow()][maze.getCol()];
        this.maze = maze;
        for (int i = 0; i < maze.getRow(); i++) {
            for (int j = 0; j < maze.getCol(); j++) {
                if (maze.getPosition(i, j).getVal() == 0) {
                    AllStates[i][j] = new MazeState(maze.getPosition(i, j));
                }
            }
        }
        source = AllStates[maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()];
        target = AllStates[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
    }

    public void resetAState() {
        for (int i = 0; i < maze.getRow(); i++) {
            for (int j = 0; j < maze.getCol(); j++) {
                if (AllStates[i][j] != null) {
                    AllStates[i][j].setPrev(null);
                    AllStates[i][j].setVisited(false);
                }
            }
        }
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> Neighbors = new ArrayList<>();
        MazeState mazeState = (MazeState) state;
        int i = mazeState.myPos.getRowIndex();
        int j = mazeState.myPos.getColumnIndex();
        if (i - 1 >= 0 && maze.getPosition(i - 1, j).getVal() == 0) {
            Neighbors.add(AllStates[i - 1][j]);
            AllStates[i - 1][j].setWeight(WEIGHT);
        }
        if (i - 1 >= 0 && j + 1 < maze.getCol() && maze.getPosition(i - 1, j + 1).getVal() == 0 && (maze.getPosition(i - 1, j).getVal() == 0 || maze.getPosition(i, j + 1).getVal() == 0)) {
            Neighbors.add(AllStates[i - 1][j + 1]);
            AllStates[i - 1][j + 1].setWeight(WEIGHT + 1);
        }
        if (j + 1 < maze.getCol() && maze.getPosition(i, j + 1).getVal() == 0) {
            Neighbors.add(AllStates[i][j + 1]);
            AllStates[i][j + 1].setWeight(WEIGHT);
        }
        if (i + 1 < maze.getRow() && j + 1 < maze.getCol() && maze.getPosition(i + 1, j + 1).getVal() == 0 && (maze.getPosition(i + 1, j).getVal() == 0 || maze.getPosition(i, j + 1).getVal() == 0)) {
            Neighbors.add(AllStates[i + 1][j + 1]);
            AllStates[i + 1][j + 1].setWeight(WEIGHT + 1);
        }
        if (i + 1 < maze.getRow() && maze.getPosition(i + 1, j).getVal() == 0) {
            Neighbors.add(AllStates[i + 1][j]);
            AllStates[i + 1][j].setWeight(WEIGHT);
        }
        if (i + 1 < maze.getRow() && j - 1 >= 0 && maze.getPosition(i + 1, j - 1).getVal() == 0 && (maze.getPosition(i + 1, j).getVal() == 0 || maze.getPosition(i, j - 1).getVal() == 0)) {
            Neighbors.add(AllStates[i + 1][j - 1]);
            AllStates[i + 1][j - 1].setWeight(WEIGHT + 1);
        }
        if (j - 1 >= 0 && maze.getPosition(i, j - 1).getVal() == 0) {
            Neighbors.add(AllStates[i][j - 1]);
            AllStates[i][j - 1].setWeight(WEIGHT);
        }
        if (i - 1 >= 0 && j - 1 >= 0 && maze.getPosition(i - 1, j - 1).getVal() == 0 && (maze.getPosition(i - 1, j).getVal() == 0 || maze.getPosition(i, j - 1).getVal() == 0)) {
            Neighbors.add(AllStates[i - 1][j - 1]);
            AllStates[i - 1][j - 1].setWeight(WEIGHT + 1);
        }
        return Neighbors;
    }

    @Override
    public AState getFirst() {
        return source;
    }

    @Override
    public AState getTarget() {
        return target;
    }

}
