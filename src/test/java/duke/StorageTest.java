package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testLoad() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            Storage storage = new Storage("./testFile.txt");
            assertEquals(file, storage.load());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testRewrite() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            Storage storage = new Storage("./testFile.txt");
            storage.rewrite("T | 1 | Apply for internships");

            Scanner sc = new Scanner(file);
            String toDoTask = sc.nextLine();

            assertEquals("T | 1 | Apply for internships", toDoTask);
        } catch (IOException e) {
            fail();
        }
    }
}
