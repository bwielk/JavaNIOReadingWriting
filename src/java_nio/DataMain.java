package java_nio;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DataMain
{
    public static void main(String[] args) throws IOException {
        try (FileOutputStream binaryFile = new FileOutputStream("data.dat");
            FileChannel binaryChannel = binaryFile.getChannel()){

            byte[] outputBytes = "Hello World! Hope you're well".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            int numOfBytes = binaryChannel.write(buffer);
            System.out.println("Number of bytes: " + numOfBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(24);
            intBuffer.flip();
            numOfBytes = binaryChannel.write(intBuffer);
            System.out.println("Number of bytes: " + numOfBytes);

            intBuffer.flip();
            intBuffer.putInt(-224);
            intBuffer.flip();
            numOfBytes = binaryChannel.write(intBuffer);
            System.out.println("Number of bytes: " + numOfBytes);

//            Path dataPath = FileSystems.getDefault().getPath("data.txt");
//            Files.write(dataPath, "\nnew line 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
//            List<String> lines = Files.readAllLines(dataPath);
//            int i = 0;
//            for(String line : lines){
//                i++;
//                System.out.println("Line " + i + ": " + line);
//            }
        }catch(IOException e){
            System.out.println("IOException caught");
        }catch(BufferOverflowException e){
            e.printStackTrace();
        }
    }
}
