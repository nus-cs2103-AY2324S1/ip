package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void TaskListConstructorTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size(), "Test initialisation of empty TaskList");
    }

    @Test
    public void TaskListOverloadedConstructorTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        assertEquals(1, taskList.size(), "Test initialisation of TaskList");
    }

    @Test
    public void TaskListMethodsTest() {
        TaskList tasks = new TaskList();

        ToDo todo = new ToDo("read book");
        tasks.add(todo);
        assertEquals(1, tasks.size(), "Test addition of task");

        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-06-06"));
        tasks.add(deadline);

        Event event = new Event("orientation week", LocalDate.parse("2023-07-31"),
                LocalDate.parse("2023-08-04"));
        tasks.add(event);

        tasks.delete(0);
        assertEquals(2, tasks.size(), "Test deletion of task");
    }
}
