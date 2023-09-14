package duke;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private TaskList taskList;
    @Test
    public void test_Save() throws IOException {
        this.taskList = new TaskList();
        String filepath = "./data/duke.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write("");
        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo("read book", "todo read book"));
        }
        Storage.save(taskList);
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            for (int i = 0; i < 5; i++) {
                assertEquals("todo read book", br.readLine());
            }
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void test_Invalid_File() {
        TaskList taskList = new TaskList();
        String filepath = "nonexistent.txt";
        Storage.save(taskList);
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            for (int i = 0; i < 5; i++) {
                assertEquals("todo read book", br.readLine());
            }
        } catch (IOException e) {
            // ignore
        }
    }
}
