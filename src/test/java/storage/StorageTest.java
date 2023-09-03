package storage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private Storage storage;
    private Path path;
    @BeforeEach
    public void setup() {
        this.storage = new Storage("./test/data/testfile.txt");
        this.path = Path.of("./test/data/testfile.txt");
        System.out.println(this.path.toAbsolutePath());
    }

    @AfterAll
    public static void tearDown() {
        try {
            Files.deleteIfExists(Path.of("./test/data/testfile.txt"));
            Files.deleteIfExists(Path.of("./test/data"));
            Files.deleteIfExists(Path.of("./test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCreateFileIfNotExists() {
        this.storage.createFileIfNotExists();
        assertTrue(Files.exists(this.path));
    }

   @Test
    public void testSaveTasksToFile() {
        TaskList tasks = new TaskList();
        Task taskOne = new ToDo("eat");
        tasks.add(taskOne);
        this.storage.saveTasksToFile(tasks);
        StringBuilder actualOutput = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path.toFile()))){
            String line;
            while((line = reader.readLine()) != null) {
                actualOutput.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String expectedOutput = "[T][ ] eat\n";
        assertEquals(expectedOutput,actualOutput.toString());
    }

    @Test
    public void testLoadTasksFromFile() {
        String expectedOutput = "[T][ ] eat";
        TaskList tasks = null;
        try {
            FileWriter writer = new FileWriter(this.path.toFile());
            writer.write("[T][ ] eat");
            writer.close();
            tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expectedOutput, tasks.get(1).toString());
    }

}