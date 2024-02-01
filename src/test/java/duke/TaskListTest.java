package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTask_todoTask_success() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("./data/test.txt");
        Todo task = new Todo("Buy groceries");
        tasks.addTask(task, storage);
        assertEquals(1, tasks.getTasks().size());
    }

    @Test
    public void deleteTask_success() {
        TaskList tasks = new TaskList();
        Todo task1 = new Todo("Buy groceries");
        Todo task2 = new Todo("Do dishes");
        Storage storage = new Storage("./data/test.txt");
        tasks.addTask(task1, storage);
        tasks.addTask(task2, storage);
        assertEquals(2, tasks.getTasks().size());
        tasks.deleteTask(1, storage);
        assertEquals(1, tasks.getTasks().size());
    }

    @Test
    public void deleteTask_invalidTask_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Todo task1 = new Todo("Buy groceries");
        Todo task2 = new Todo("Do dishes");
        Storage storage = new Storage("./data/test.txt");
        tasks.addTask(task1, storage);
        tasks.addTask(task2, storage);
        assertEquals(2, tasks.getTasks().size());
        try {
            new DeleteCommand("delete 3").execute(tasks , ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but arguments to delete is invalid", e.getMessage());
        }
    }


}
