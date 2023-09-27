package devybot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import devybot.exceptions.EmptyDescriptionException;
import devybot.exceptions.TaskIndexOutOfBoundsException;
import devybot.util.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void addTodoTask_validDescription_taskAdded() throws EmptyDescriptionException {
        taskList.addTodoTask("todo Buy groceries");
        assertEquals(1, taskList.size());
    }

    @Test
    public void addTodoTask_emptyDescription_emptyDescriptionExceptionThrown() {
        try {
            taskList.addTodoTask("todo");
            fail("Expected EmptyDescriptionException was not thrown");
        } catch (EmptyDescriptionException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void markTaskAsDone_invalidIndex_taskIndexOutOfBoundsExceptionThrown() {
        try {
            taskList.markTaskAsDone(0);
            fail("Expected TaskIndexOutOfBoundsException was not thrown");
        } catch (TaskIndexOutOfBoundsException e) {
            assertEquals("\u2639 OOPS!!! The task index 1 does not exist.", e.getMessage());
        }
    }
}
