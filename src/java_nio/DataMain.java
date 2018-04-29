package java_nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DataMain
{
    public static void main(String[] args) throws IOException {

        //WRITING THE DATA TO THE data.dat FILE

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

            //READING THE DATA FROM THE data.dat FILE

            RandomAccessFile randomAccessFile = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = randomAccessFile.getChannel();
            outputBytes[0] = 'a';
            outputBytes[1] = 'b';
            buffer.flip();
            long numBytesRead = channel.read(buffer);
            if(buffer.hasArray()){
                System.out.println("byte buffer = " + new String(buffer.array()));
            }

            //AN EXAMPLE OF AN ABSOLUTE READ
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            //AN EXAMPLE OF A RELATIVE READ
            //
            //            intBuffer.flip();
            //            numBytesRead = channel.read(intBuffer);
            //            intBuffer.flip();
            //            System.out.println(intBuffer.getInt());
            //            intBuffer.flip();
            //            numBytesRead = channel.read(intBuffer);
            //            intBuffer.flip();
            //            System.out.println(intBuffer.getInt());
            //            intBuffer.flip();
            //            channel.close();
            //            randomAccessFile.close();

            //Reading the file with the RandomAccessFile
//            byte[] b = new byte[outputBytes.length];
//            randomAccessFile.read(b);
//            System.out.println(new String(b));
//
//            long int1 = randomAccessFile.readInt();
//            long int2 = randomAccessFile.readInt();
//            System.out.println("Int 1 = " + int1 + "; Int 2 = " + int2);

        }catch(IOException e){
            System.out.println("IOException caught");
        }catch(BufferOverflowException e){
            e.printStackTrace();
        }
    }
}
