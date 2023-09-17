package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import org.junit.jupiter.api.Test;
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
        assertThrows(CorruptedFileException.class, corruptionList::loadFromDisk);
    }

    @Test
    public void markTest() {
        Storage tester = new Storage();
        TaskList testerList = new TaskList(tester);
        try {
            tester.init("./data/testStorage.txt");
        } catch (IOException e) {
            fail();
        }
        assertDoesNotThrow(testerList::loadFromDisk);
        assertDoesNotThrow(() -> testerList.addTask(new ToDo("name")));
        assertDoesNotThrow(() -> assertTrue(testerList.setMark(1, true)));
        assertDoesNotThrow(() -> assertTrue(testerList.setMark(1, true)));
        assertDoesNotThrow(() -> assertTrue(testerList.setMark(1, false)));
        assertDoesNotThrow(() -> assertFalse(testerList.setMark(-1, true)));
        assertDoesNotThrow(tester::clear);
    }
}
