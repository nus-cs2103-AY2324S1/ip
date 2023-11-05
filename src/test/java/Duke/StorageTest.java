package Duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {
    @Test
    public void testAddToFile(){
        try {
            File f = new File("testfile.txt");
            FileWriter fw = new FileWriter(f, false);
            fw.append("hello");
            fw.close();
            Storage storage = new Storage("testfile.txt");
            storage.addToFile("bye");
            Scanner scanner = new Scanner(f);
            assertEquals("hellobye",scanner.nextLine());
        }catch (Exception e){
            System.out.println("Exception while testing: "+e.getMessage());
        }
    }

}
