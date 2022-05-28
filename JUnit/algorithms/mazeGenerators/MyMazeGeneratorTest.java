package algorithms.mazeGenerators;

import static org.junit.jupiter.api.Assertions.*;

class MyMazeGeneratorTest {
    @org.junit.jupiter.api.Test
    void generate() {

        MyMazeGenerator Mg= new MyMazeGenerator();
        Maze maze;
        try {
            for (int i=1;i<10;i++){
                for (int j=1;j<10;j++){
                    maze = Mg.generate(i,j);
                    assertFalse(maze.col!=j||maze.getRow()!=i);
                }
            }
        }
        catch (Exception e){
            fail();
        }
        assertNull(Mg.generate(0, 0));
        assertNull(Mg.generate(32, -20));
        assertNull(Mg.generate(0, 100));


    }
}