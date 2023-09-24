package duke.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.TodoTask;

public class OutputServiceTest {
    @Test
    public void testFormatTaskList_appendsCorrectIndex() {
        OutputService outputService = new OutputService();
        List<Task> taskList = List.of(
            new TodoTask("Task 1"),
            new TodoTask("Task 2"),
            new TodoTask("Task 3")
        );

        List<String> expected = List.of(
            String.format("1. %s", taskList.get(0)),
            String.format("2. %s", taskList.get(1)),
            String.format("3. %s", taskList.get(2))
        );

        List<String> actual = outputService.formatTaskList(taskList);

        assertEquals(expected, actual);
    }
}
