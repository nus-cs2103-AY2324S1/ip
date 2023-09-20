package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void initialiseTest1() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void initialiseTest2() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<>(0), new Storage(""));
        try {
            tasks.createTask(Parser.TaskClass.TODO, "return books");
        } catch (BeeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTask(0).toString());
    }
    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<>(0), new Storage(""));
        try {
            tasks.createTask(Parser.TaskClass.TODO, "return books");
        } catch (BeeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTask(0).toString());
    }
}
