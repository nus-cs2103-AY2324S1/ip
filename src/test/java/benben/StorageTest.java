package benben;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void loadFromStorage_emptyFile_LengthIsZero() {
        Storage storage = new Storage("storage_test_0.txt");
        TaskList tasks = new TaskList(storage.load());
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void loadFromStorage_addTask_correctFileConetnt() {
        try {
            String filepath = "storage_test_2.txt";
            Storage storage = new Storage(filepath);
            // empty the list first;
            TaskList tasks = new TaskList();
            storage.write(new TaskList());
            tasks.add(new Todo("test"));
            storage.write(tasks);
            File file = new File(filepath);
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            assertEquals(line, "T | 0 | test");
        } catch (FileNotFoundException e) {

        }
    }
}
