package algorithms.maze3D;

public abstract class  AMaze3DGenerator implements IMaze3DGenerator {


    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int col)
    {
        long start = System.currentTimeMillis();
        this.generate(depth,row, col);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
