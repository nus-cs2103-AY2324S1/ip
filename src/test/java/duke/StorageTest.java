package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void test() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("./src/main/data/test.txt");
            ArrayList<Task> list = storage.load();
            Task t = list.get(2);
            assertEquals("E--1--party --2023-09-02--2023-09-03", t.toFileString());

        } catch(IOException e) {
            fail();
        }
    }
}
