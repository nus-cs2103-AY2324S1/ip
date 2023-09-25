package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import taskcreator.CreateDeadlineTask;
import taskcreator.CreateEventTask;
import taskcreator.CreateTodoTask;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * This class tests if the task creator classes create the correct type of objects.
 */
public class TaskCreatorTests {
    private final ArrayList<Task> testTaskList = new ArrayList<>();
    private final CreateDeadlineTask deadlineTaskCreator = new CreateDeadlineTask();
    private final CreateTodoTask todoTaskCreator = new CreateTodoTask();
    private final CreateEventTask eventTaskCreator = new CreateEventTask();

    /**
     * This method tests if the objects made are subtypes of the Task class.
     */
    @Test
    public void testTaskCreators() {
        assertTrue(todoTaskCreator.create("todo Tutorial", testTaskList) instanceof Todo);
        assertTrue(deadlineTaskCreator
                .create("deadline Tutorial /by 2023-09-23", testTaskList) instanceof Deadline);
        assertTrue(eventTaskCreator
                .create("event Tutorial /from 2023-09-22 /to 2023-09-23", testTaskList) instanceof Event);
    }
}
