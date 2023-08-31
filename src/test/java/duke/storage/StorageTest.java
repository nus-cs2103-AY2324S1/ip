package duke.storage;

import duke.Duke;
import duke.parser.Parser;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;
import duke.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void loadTasksFromFile_fileNotFound_errorMessageShown(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        storage.loadTasksFromFile(new File("./data/storage.txt"), taskList);
        assertEquals("Error scanning file!", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void loadTasksFromFile_fileWithMixedInput_tasksLoadedAccordingly(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        storage.loadTasksFromFile(new File("./data/storagetest.txt"), taskList);

        TaskList expectedList = new TaskList();
        expectedList.addTask(new ToDoTask("read book", true));
        LocalDateTime by = LocalDateTime.parse("2023-01-01T18:00");
        expectedList.addTask(new DeadlineTask("do homework", by, false));
        LocalDateTime from = LocalDateTime.parse("2023-01-01T12:00");
        LocalDateTime to = LocalDateTime.parse("2023-01-01T14:00");
        expectedList.addTask(new EventTask("dance class", from, to, false));

        assertEquals(expectedList.getTaskListData(), taskList.getTaskListData());
    }
}
