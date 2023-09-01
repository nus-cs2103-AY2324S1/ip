package OOP;  //same package as the class being tested

import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import Tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private static final String TEST_STORAGE_FILE_PATH = "data/test.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Create a dummy file with the specified content
        try (FileWriter writer = new FileWriter(TEST_STORAGE_FILE_PATH)) {
            writer.write("D | 0 | return book | 2019-12-02 1800\n");
            writer.write("T | 0 | runaway\n");
            writer.write("E | 0 | project meeting | Mon 2pm | 4pm\n");
        }
    }
    @AfterEach
    void tearDown() {
        // Clean up: delete the temporary file if it wasn't deleted in the test
        File tempFile = new File(TEST_STORAGE_FILE_PATH);
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
    @Test
    public void load_correctTaskFormat_success() {
        List<Task> tasks = new Storage(TEST_STORAGE_FILE_PATH).load();
        assertEquals("[D][] return book (by: 1800, 02 Dec, 2019)", tasks.get(0).toString());
        assertEquals("[T][] runaway", tasks.get(1).toString());
        assertEquals("[E][] project meeting (from: Mon 2pm to: 4pm)", tasks.get(2).toString());
    }
    @Test
    public void save_correctTaskFormat_success() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("CS2103T Homework", false));
        tasks.add(new Deadline("CS2101 Presentation", false, LocalDateTime.of(2023, Month.OCTOBER, 10, 10, 0)));
        tasks.add(new Event("Polling day", false, "1 Sep 2023 8am", "8pm"));
        new Storage(TEST_STORAGE_FILE_PATH).save(new TaskList(tasks));
        try {
            List<String> lines = Files.readAllLines(Paths.get(TEST_STORAGE_FILE_PATH));
            assertEquals("T | 0 | CS2103T Homework",lines.get(0));
            assertEquals("D | 0 | CS2101 Presentation | 2023-10-10 1000",lines.get(1));
            assertEquals("E | 0 | Polling day | 1 Sep 2023 8am | 8pm",lines.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
