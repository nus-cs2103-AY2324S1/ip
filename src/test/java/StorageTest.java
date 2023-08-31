import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExceptionThrown() throws DukeException {
        Storage storage = new Storage("data/duke.txt");
        File file = new File("data/duke.txt");
        DukeException exception = assertThrows(DukeException.class, () -> {
            file.delete();
            storage.load();
        });

        String expectedMessage = "OOPS! File cannot be loaded.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testLoad() throws DukeException {
        Storage storage = new Storage("data/test.txt");
        TaskList taskList = storage.load();
        TaskList actualList = new TaskList();
        Todo todo = new Todo("borrow book");
        actualList.add(todo, false);
        assertEquals(taskList.get(0), actualList.get(0));
    }

}
