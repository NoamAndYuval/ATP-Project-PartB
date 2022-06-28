package IO;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * Writes the specified byte to this output stream. The general
     * contract for {@code write} is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument {@code b}. The 24
     * high-order bits of {@code b} are ignored.
     * <p>
     * Subclasses of {@code OutputStream} must provide an
     * implementation for this method.
     *
     * @param b the {@code byte}.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an {@code IOException} may be thrown if the
     *                     output stream has been closed.
     */
    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {

        byte[] bytes1 = ByteBuffer.wrap(Arrays.copyOfRange(b, 24, b.length)).array();

        ArrayList<Integer> list = new ArrayList<Integer>();
        int count = 0;
        boolean Flag = true;
        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] == 0) {
                if (Flag) {
                    count++;
                } else {
                    list.add(count);
                    Flag = true;
                    count = 1;
                }
            } else {
                if (Flag) {
                    list.add(count);
                    Flag = false;
                    count = 1;

                } else {
                    count++;
                }

            }
            if (count == 255) {
                list.add(255);
                count = 0;
                Flag=!Flag;
            }
        }
        if(count>0)
            list.add(count);
        byte[] output = new byte[24+list.size()];
        for (int i = 0; i <24 ; i++)
            output[i]=b[i];
        for (int i = 0; i <list.size() ; i++)
            output[i+24]= list.get(i).byteValue();
        out.write(output);

    }

}
