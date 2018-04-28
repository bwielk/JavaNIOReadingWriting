package java_nio;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DataMain
{
    public static void main(String[] args) throws IOException {
        try{
//            FileInputStream file = new FileInputStream("data.txt");
//            FileChannel channel = file.getChannel();//open for reading
            Path dataPath = FileSystems.getDefault().getPath("data.txt");
            Files.write(dataPath, "\nnew line 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(dataPath);
            int i = 0;
            for(String line : lines){
                i++;
                System.out.println("Line " + i + ": " + line);
            }
        }catch(IOException e){
            System.out.println("IOException caught");
        }
    }
}
