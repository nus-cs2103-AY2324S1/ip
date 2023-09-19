package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_taskAdded() {
        Task task = new Todo("Read a book");
        taskList.add(task);

        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void deleteTask_validIndex_taskDeleted() {
        Task task = new Todo("Read a book");
        taskList.add(task);

        Task deletedTask = taskList.delete(1);
        assertEquals(task, deletedTask);
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void markTask_validIndex_taskMarked() {
        Task task = new Todo("Read a book");
        taskList.add(task);

        Task markedTask = taskList.mark(1);
        assertTrue(markedTask.getStatus());
    }

    @Test
    public void unmarkTask_validIndex_taskUnmarked() {
        Task task = new Todo("Read a book");
        taskList.add(task);
        taskList.mark(1);

        Task unmarkedTask = taskList.unmark(1);
        assertFalse(unmarkedTask.getStatus());
    }

    @Test
    public void findTasksByKeyword_keywordPresent_tasksFound() {
        Task task1 = new Todo("Read a book");
        Task task2 = new Todo("Write a report");
        taskList.add(task1);
        taskList.add(task2);

        TaskList foundTasks = taskList.findTasksByKeyword("Read");
        assertEquals(1, foundTasks.getListSize());
    }

    @Test
    public void sortDeadlines_multipleDeadlines_tasksSorted() {
        Task task1 = new Deadline("Finish report", "2023-10-01 1800");
        Task task2 = new Deadline("Start report", "2023-09-01 1900");
        Task task3 = new Event("Report presentation", "2023-11-01 1900", "2023-11-01 2000");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        taskList.sortDeadlines();
        assertEquals("Start report", taskList.stream().findFirst().get().getDescription());
    }
}
