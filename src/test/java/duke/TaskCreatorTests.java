package duke;

import org.junit.jupiter.api.Test;
import task_creator.CreateDeadlineTask;
import task_creator.CreateEventTask;
import task_creator.CreateTodoTask;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests if the task creator classes create the correct type of objects.
 */
public class TaskCreatorTests {
    ArrayList<Task> testTaskList = new ArrayList<>();
    CreateDeadlineTask deadlineTaskCreator = new CreateDeadlineTask();
    CreateTodoTask todoTaskCreator = new CreateTodoTask();
    CreateEventTask eventTaskCreator = new CreateEventTask();

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
