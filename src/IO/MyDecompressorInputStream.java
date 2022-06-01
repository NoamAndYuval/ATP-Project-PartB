package IO;

import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDecompressorInputStream extends InputStream {
    InputStream in ;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to
     * {@code 255}. If no byte is available because the end of the stream
     * has been reached, the value {@code -1} is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or {@code -1} if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return 0;
    }
    public int read(byte[] b)throws IOException{
        try {
            byte[] InputByteArray = in.readAllBytes();
            byte [] bytes1 = ByteBuffer.wrap(Arrays.copyOfRange(InputByteArray,24,InputByteArray.length)).array();
        StringBuilder result = new StringBuilder();
            for (int i = 0; i <24 ; i++) {
                b[i]=InputByteArray[i];
            }
        for (int i = 0; i < bytes1.length; i++) {
            byte val= bytes1[i];
            for (int j=0; j<8; j++) {
                result.append((int)(val >> (8-(j+1)) & 0x0001));
            }
        }
        String str = result.toString();
            for (int i = 24; i <b.length ; i++) {
                int temp = Character.getNumericValue(str.charAt(i-24));
                b[i]= Integer.valueOf(temp).byteValue();
            }






        }catch (Exception e){
            return -1;
        }



        return 1;
    }
}
