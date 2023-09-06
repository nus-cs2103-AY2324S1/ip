package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void initialiseTest1() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void initialiseTest2() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        assertEquals(0, tasks.size());
    }

    @Test
    public void addTaskTest1() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            tasks.addItem(Parser.taskType.TODO, "todo return books");
        } catch (DukeException e) {
            System.out.println("something went wrong");
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTaskWithIndex(0).toString());
    }

    @Test
    public void removeTaskTest1() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            tasks.addItem(Parser.taskType.TODO, "todo return books");
        } catch (DukeException e) {
            System.out.println("something went wrong");
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTaskWithIndex(0).toString());

        try {
            tasks.deleteItem("delete 1");
        } catch (DukeException e) {
            System.out.println("something went wrong");
        }
        assertEquals(0, tasks.size());
    }
}
