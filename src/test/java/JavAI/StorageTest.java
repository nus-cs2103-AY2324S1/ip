package JavAI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class StorageTest {

    private Storage storage;
    private final String FILE_PATH = "./src/main/txtFolder/JavAI.txt";

    @BeforeEach
    void setUp() {

        storage = new Storage(FILE_PATH);
    }

    @Test
    void testLoad() throws IOException {

        File testFile = new File(FILE_PATH);
        FileWriter writer = new FileWriter(testFile);
        writer.write("[T][X] Sample Todo\n");
        writer.write("[D][X] Sample Deadline ( by: Dec 31 2023 23:59 )\n");
        writer.close();
        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(2, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
    }

    @Test
    void testTaskListWriter() throws IOException {

        ArrayList<Task> tasksToWrite = new ArrayList<>();
        tasksToWrite.add(new Todo("Write JUnit Tests"));
        tasksToWrite.add(new Deadline("Finish Project", "2023-12-31", "2359"));
        storage.taskListWriter(tasksToWrite);
        File testFile = new File(FILE_PATH);
        Scanner scanner = new Scanner(testFile);
        StringBuilder fileContent = new StringBuilder();
        while (scanner.hasNextLine()) {
            fileContent.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        String expectedContent = "[T][ ] Write JUnit Tests\n" +
                "[D][ ] Finish Project ( by: Dec 31 2023 23:59 )\n";
        assertEquals(expectedContent, fileContent.toString());
    }
}

