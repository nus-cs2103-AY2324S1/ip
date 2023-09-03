package Jeoe.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

public class TodoCommandTest {
    protected StorageManager sm;
    protected TaskManager tm;
    protected Ui ui = new Ui();

    @Test
    public void testAddTodo() {
        try {
            String filePath = System.getProperty("user.dir") + "/storage/testTaskListData.txt";
            sm = new StorageManager(filePath);
            sm.deleteAllInFile();
            tm = new TaskManager(sm.load());
            TodoCommand todoTest = new TodoCommand("todo test");
            todoTest.execute(tm, ui, sm);
            assertEquals("1. [T][ ] test\n", tm.toString());
        } catch (Exception e) {
            System.out.println("Exception in add to do test");
        }
    }
}
