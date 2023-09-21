package duke;

import duke.command.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void addAndDeleteTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        TodoTask todoTask = new TodoTask("Buy groceries", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), true, TaskPriority.LOW);

        // Add a task and verify it's added
        taskList.addTask(todoTask);
        assertEquals(1, taskList.getTotalTasks());

        // Delete the task and verify it's removed
        Task deletedTask = taskList.deleteTask(0);
        assertEquals(0, taskList.getTotalTasks());
        assertEquals(todoTask, deletedTask);
    }

    @Test
    public void markTaskAsDoneTest() throws DukeException {
        TaskList taskList = new TaskList();
        EventTask eventTask = new EventTask("Birthday party", LocalDate.parse("2023-12-31"), false, TaskPriority.MEDIUM);
        taskList.addTask(eventTask);

        // Mark the task as done and verify it's marked
        taskList.markAsDone(0);
        assertTrue(taskList.isTaskDone(0));
    }

    @Test
    public void markTaskAsNotDoneTest() throws DukeException {
        TaskList taskList = new TaskList();
        EventTask eventTask = new EventTask("Birthday party", LocalDate.parse("2023-12-31"), true, TaskPriority.MEDIUM);
        taskList.addTask(eventTask);

        // Mark the task as done and verify it's marked
        taskList.markAsNotDone(0);
        assertTrue(!taskList.isTaskDone(0));
    }
}
