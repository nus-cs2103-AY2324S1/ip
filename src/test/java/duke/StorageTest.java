package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    Storage storage;

    String filePath = "ChewieTest.json";


    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    @Test
    public void load_noSuchFile_newSave() {
        TaskList list = new TaskList();
        storage.load(list, "");
        assertTrue(storage.isNewSave());
    }
    @Test
    public void load_validFile_success() {
        TaskList result = new TaskList();
        storage.load(result,"ChewieTest.json");

        TaskList expected = new TaskList();
        expected.add(new ToDo("run"));
        expected.add(new Deadline("jump", LocalDate.parse("2020-10-10", DateTimeFormatter.ISO_DATE)));
        expected.add(new Events("kill", LocalDate.parse("2020-10-10",DateTimeFormatter.ISO_DATE),
                LocalDate.parse("2020-12-26",DateTimeFormatter.ISO_DATE)));
        expected.mark(1);

        assertEquals(result,expected);
    }
}
