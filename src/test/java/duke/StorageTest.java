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
        expected.assignTaskWithTag(0, "fast");
        expected.add(new Deadline("cs2105", LocalDate.parse("2023-09-25", DateTimeFormatter.ISO_DATE)));
        expected.add(new Events("Mnight", LocalDate.parse("2023-09-23",DateTimeFormatter.ISO_DATE),
                LocalDate.parse("2023-09-23",DateTimeFormatter.ISO_DATE)));
        expected.add(new ToDo("jump"));

        assertEquals(result,expected);
    }
}
