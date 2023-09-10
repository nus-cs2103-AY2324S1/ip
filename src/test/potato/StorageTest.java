package potato;

import org.junit.jupiter.api.Test;
import potato.command.ExitCommand;
import potato.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void StorageTest1() throws IOException {
        Storage s = new Storage("./potato.txt");
        ArrayList<Task> t = s.loadTask();
        assertEquals(t, new ArrayList<>());
    }
}
