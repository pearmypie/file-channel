import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(256); // allocate the buffer
        int bytesRead = channel.read(buffer); // write into buffer from channel

        while (bytesRead != -1) {
            System.out.println("Read: " + bytesRead + " bytes");

            buffer.flip(); // switch from write mode to read mode
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            } // read data from buffer

            buffer.clear();
            bytesRead = channel.read(buffer); // <-- do not remove
        }

        file.close();
    }
}