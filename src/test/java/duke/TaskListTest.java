package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import dukeexception.CorruptedFileException;
import task.ToDo;

public class TaskListTest {
    @Test
    public void corruptionTest() {
        Storage corrupted = new Storage();
        TaskList corruptionList = new TaskList(corrupted);
    try {
        corrupted.init("./data/corruptedStorage.txt");
    } catch (IOException e) {
        fail();
    }
        assertThrows(CorruptedFileException.class, () -> corruptionList.loadFromDisk());
    }

    @Test
    public void TaskListTest() {
        Storage tester = new Storage();
        TaskList testerList = new TaskList(tester);
        try {
            tester.init("./data/testStorage.txt");
        } catch (IOException e) {
            fail();
        }
        assertDoesNotThrow(() -> testerList.loadFromDisk());
        assertDoesNotThrow(() -> testerList.addTask(new ToDo("name")));
        assertDoesNotThrow(() -> {
            assertEquals(true, testerList.setMark(0, true));
        });
        assertDoesNotThrow(() -> {
            assertEquals(true, testerList.setMark(0, true));
        });
        assertDoesNotThrow(() -> {
            assertEquals(true, testerList.setMark(0, false));
        });
        assertDoesNotThrow(() -> {
            assertEquals(false, testerList.setMark(-1, true));
        });
        assertDoesNotThrow(() -> tester.clear());
    }
}
