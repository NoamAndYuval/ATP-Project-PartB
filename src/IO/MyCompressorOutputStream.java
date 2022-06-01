package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCompressorOutputStream extends OutputStream {

     private OutputStream out ;

    public MyCompressorOutputStream(OutputStream out) {
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
    public void write(byte[]bytes)throws IOException{
        byte[] bytes1 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 24, bytes.length)).array();
        List<Byte> byteArrayList = new ArrayList<>();
        for (int i = 0; i <24 ; i++) {
            byteArrayList.add(bytes[i]);
        }
        int[] temp = new int[8];
        int index = 0;
        for (int i = 0; i < bytes1.length; i++) {
            temp[index] = bytes1[i];
            index++;
            if (index == 8) {
                byteArrayList.add(ConvertBoolArrayToByte(temp));
                index=0;
                temp = new int[8];
            }

        }
        if (index!=0){
            byteArrayList.add(ConvertBoolArrayToByte(temp));
        }

        byte[] sul = new byte[byteArrayList.size()];
        for(int i= 0 ; i<sul.length;i++)
            sul[i]=byteArrayList.get(i);

        out.write(sul);
    }
    private  byte ConvertBoolArrayToByte(int[] source) {

        byte result = 0;
        // This assumes the array never contains more than 8 elements!
        int index = 8 - source.length;

        // Loop through the array
        for (int b : source) {
            // if the element is 'true' set the bit at that position
            if (b == 1)
                result |= (byte) (1 << (7 - index));

            index++;
        }

        return result;

    }
}
