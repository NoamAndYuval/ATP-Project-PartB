package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(1000,1000 );
//        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch(),maze);
        solveProblem(searchableMaze, new DepthFirstSearch(),maze);
        solveProblem(searchableMaze, new BestFirstSearch(),maze);
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher,Maze maze) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);

        System.out.println(searcher.getName()+"algorithm - nodes evaluated: "+ searcher.getNumberOfNodesEvaluated());
//Printing Solution Path
        System.out.println("Solution path:");
//        displaysol(solution,maze,searcher);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(""+ i+"." +solutionPath.get(i));
        }
    }
//    private static void displaysol(Solution solution,Maze maze, ISearchingAlgorithm
//            searcher){
//        Color c;
//        int row=maze.getRow();
//        int col =maze.getCol();
//        BufferedImage image = new BufferedImage(row/*Width*/, col/*height*/, BufferedImage.TYPE_INT_ARGB);
//
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (maze.getStartPosition().getRowIndex()==i&&maze.getStartPosition().getColumnIndex()==j){
//                    c = new Color(0, 255, 0);
//                }
//                else if (maze.getGoalPosition().getRowIndex()==i&&maze.getGoalPosition().getColumnIndex()==j){
//
//                    c = new Color(255, 0, 0);
//                }
//                else if (contain(solution,maze.getPosition(i,j))){
//                    c = new Color(0, 150, 255);
//                }
//                else if ((maze.getPosition(i,j).getVal()==0)){
//                    c = new Color(255, 255, 255);
//                }
//                else {
//                    c = new Color(0, 0, 0);
//                }
//                image.setRGB(i, j, c.getRGB());
//            }
//        }
//
//        JFrame frame = new JFrame(searcher.getName());
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(row*20+100, col*20+100);
//        JPanel pane = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(image, 15, 15,row*7,col*7,null );
//            }
//        };
//        pane.setSize(650,650);
//        pane.setPreferredSize(new Dimension(650, 650));
//        frame.add(pane,BorderLayout.CENTER);
//        frame.setName(searcher.getName());
//        frame.setVisible(true);
//    }
//    private static boolean contain(Solution solution, Position position){
//        for (AState state:solution.getSolutionPath()
//             ) {
//            MazeState state1=(MazeState)state;
//            if (position==state1.getMyPos()){
//                return true;
//            }
//        }
//        return false;
//    }
}