package duke.tasks;

import duke.Duke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private Duke duke;

    @BeforeEach
    public void setUp() {
        duke = new Duke("./data/test.txt"); // Use a test file path
    }

    @Test
    public void testAddTask() {
        Task task = new ToDoTask("Task");
        duke.getTaskList().addTask(task);
        ArrayList<Task> tasks = duke.getTaskList().getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void testDeleteTask() {
        Task task1 = new ToDoTask("Task1");
        Task task2 = new ToDoTask("Task2");
        duke.getTaskList().addTask(task1);
        duke.getTaskList().addTask(task2);

        duke.getTaskList().deleteTask(0);
        ArrayList<Task> tasks = duke.getTaskList().getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task2, tasks.get(0));
    }

    @Test
    public void testMarkTaskAsDone() {
        Task task = new ToDoTask("Task");
        duke.getTaskList().addTask(task);
        duke.getTaskList().markTaskAsDone(0);
        ArrayList<Task> tasks = duke.getTaskList().getTasks();
        assertEquals(true, tasks.get(0).isDone());
    }

    @Test
    public void testUnmarkTask() {
        Task task = new ToDoTask("Task");
        duke.getTaskList().addTask(task);
        duke.getTaskList().markTaskAsDone(0);
        duke.getTaskList().unmarkTask(0);
        ArrayList<Task> tasks = duke.getTaskList().getTasks();
        assertEquals(false, tasks.get(0).isDone());
    }
}
