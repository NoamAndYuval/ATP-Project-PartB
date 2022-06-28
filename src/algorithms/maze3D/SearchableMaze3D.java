package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.SearchableMaze;

import java.util.ArrayList;

public class SearchableMaze3D extends SearchableMaze {
    protected Maze3DState[][][] maze3DStates;

    public SearchableMaze3D(Maze3D maze) {
        super(maze);
        maze3DStates = new Maze3DState[maze.getDepth()][maze.getRow()][maze.getCol()];
        for (int k = 0; k < maze.getDepth(); k++) {
            for (int i = 0; i < maze.getRow(); i++) {
                for (int j = 0; j < maze.getCol(); j++) {
                    if (maze.getPosition(k, i, j).getVal() == 0) {
                        maze3DStates[k][i][j] = new Maze3DState(maze.getPosition(k, i, j));
                    }
                }
            }
        }
        source = maze3DStates[maze.getStartPosition().getDepthIndex()][maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()];
        target = maze3DStates[maze.getGoalPosition().getDepthIndex()][maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
    }

    @Override
    public void resetAState() {

        for (int k = 0; k < ((Maze3D) maze).getDepth(); k++) {
            for (int i = 0; i < maze.getRow(); i++) {
                for (int j = 0; j < maze.getCol(); j++) {
                    if (maze3DStates[k][i][j] != null) {
                        maze3DStates[k][i][j].setPrev(null);
                        maze3DStates[k][i][j].setVisited(false);
                    }
                }
            }
        }
    }

    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> Neighbors = new ArrayList<AState>();
        Maze3DState mazeState = (Maze3DState) state;
        int k = ((Position3D) (mazeState.getMyPos())).getDepthIndex();
        int i = mazeState.getMyPos().getRowIndex();
        int j = mazeState.getMyPos().getColumnIndex();
        Maze3D Tmaze3D = (Maze3D) maze;
        if (i - 1 >= 0 && Tmaze3D.getPosition(k, i - 1, j).getVal() == 0) {
            Neighbors.add(maze3DStates[k][i - 1][j]);
            maze3DStates[k][i - 1][j].setWeight(WEIGHT);
        }

        if (j + 1 < Tmaze3D.getCol() && Tmaze3D.getPosition(k, i, j + 1).getVal() == 0) {
            Neighbors.add(maze3DStates[k][i][j + 1]);
            maze3DStates[k][i][j + 1].setWeight(WEIGHT);
        }

        if (i + 1 < Tmaze3D.getRow() && Tmaze3D.getPosition(k, i + 1, j).getVal() == 0) {
            Neighbors.add(maze3DStates[k][i + 1][j]);
            maze3DStates[k][i + 1][j].setWeight(WEIGHT);
        }
        if (j - 1 >= 0 && Tmaze3D.getPosition(k, i, j - 1).getVal() == 0) {
            Neighbors.add(maze3DStates[k][i][j - 1]);
            maze3DStates[k][i][j - 1].setWeight(WEIGHT);
        }
        if (k + 1 < Tmaze3D.getDepth() && Tmaze3D.getPosition(k + 1, i, j).getVal() == 0) {
            Neighbors.add(maze3DStates[k + 1][i][j]);
            maze3DStates[k + 1][i][j].setWeight(WEIGHT);
        }
        if (k - 1 >= 0 && Tmaze3D.getPosition(k - 1, i, j).getVal() == 0) {
            Neighbors.add(maze3DStates[k - 1][i][j]);
            maze3DStates[k - 1][i][j].setWeight(WEIGHT);
        }
        return Neighbors;
    }

}
