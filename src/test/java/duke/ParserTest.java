package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    /**
     * This JUnit test method verifies the correctness of the 'executeList' method in the 'Parser' class.
     * This test ensures that the 'executeList' method correctly lists and formats tasks for display.
     */
    @Test
    void testExecuteList() {
        // Create a TaskList with sample tasks
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new Deadline("Task 2", LocalDateTime.now()));
        taskList.addTask(new Event("Task 3", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));

        // Test executeList method
        String result = Parser.executeList(taskList);

        // Assert that the result contains task descriptions
        assertTrue(result.contains("[T][ ] Task 1"));
        assertTrue(result.contains("[D][ ] Task 2"));
        assertTrue(result.contains("[E][ ] Task 3"));
    }
}
