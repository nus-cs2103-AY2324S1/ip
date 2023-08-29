package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testSize() {
        Storage storage = new Storage("data/storageTestNumberOfTasks.txt");
        ArrayList<Task> taskArray = storage.loadFile();
        assertEquals(4, taskArray.size());
    }

    @Test
    public void testRewrite() {
        Storage storage = new Storage("data/storageTestRewrite.txt");
        ArrayList<Task> taskArray = new ArrayList<>();
        taskArray.add(new Todo("do well in CS2103T"));
        TaskList tasks = new TaskList(taskArray);

        try {
            FileWriter fw = new FileWriter(new File("data/storageTestRewrite.txt"));
            fw.write("test\nto\nsee\nif\noverwrite\nworks");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        storage.rewriteFile(tasks);
        assertEquals("[T][ ] do well in CS2103T", storage.loadFile().get(0).toString());
    }
}
