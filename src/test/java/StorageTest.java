import chatter.Storage;
import chatter.task.Deadline;
import chatter.task.Task;
import chatter.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testLoadTasks() throws IOException {
        try {
            Storage storage = new Storage("./data/chatter.txt");
            storage.saveFile("T, false, return book\nD, true, help, tmr");
            ArrayList<Task> result = new ArrayList<>();
            result.add(new ToDo("return book"));
            Task t = new Deadline("help", "tmr");
            t.markAsDone();
            result.add(t);
            assertEquals(result.get(0), storage.readFile().get(0));
            assertEquals(result.get(1), storage.readFile().get(1));
        } catch (IOException e) {
            fail();
        }
    }
}
