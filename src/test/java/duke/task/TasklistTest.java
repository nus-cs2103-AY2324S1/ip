package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.IllegalTaskIndexException;
import duke.exception.InvalidArgumentException;

public class TasklistTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMarkAsDone_invalidIndex_shouldThrowException() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(IllegalTaskIndexException.class, () -> taskList.markAsDone(1));
        taskList.deleteAllTasks();

    }

    @Test
    public void testAddTask_invalidTaskType_shouldThrowException() {
        TaskList taskList = new TaskList("sample.txt");
        assertThrows(InvalidArgumentException.class, () -> taskList.addTask("invalidTaskType some details"));
        taskList.deleteAllTasks();
    }


}
