package duke; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;


public class TaskListTest {
    @Test
    public void emptyTaskListLengthTest() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        assertEquals(a.length(), b.length());
    }

    @Test
    public void emptyTaskListTest() {
        try {
            Task task = Todo.of("todo read book");
            TaskList a = new TaskList();
            TaskList b = new TaskList();
            a.addTask(task);
            b.addTask(task);
            assertEquals(a.getTask(0), b.getTask(0));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
