package jeoe.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeoe.Exceptions.InvalidCommandException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

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
            tm = new TaskManager(sm.load(), filePath);
            TodoCommand todoTest = new TodoCommand("todo test");
            todoTest.execute(tm, ui, sm);
            assertEquals("1. [T][ ] test\n", tm.toString());
        } catch (Exception e) {
            System.out.println("Exception in add to do test");
        }
    }

    @Test
    public void testFailedTodo() {
        try {
            String filePath = System.getProperty("user.dir") + "/storage/testTaskListData.txt";
            sm = new StorageManager(filePath);
            sm.deleteAllInFile();
            tm = new TaskManager(sm.load(), filePath);

            String input = "todotest";
            try {
                Command todoTest = CommandParser.parse(input);
                System.out.println("Exception is expected in add to do test, test failed");
            } catch (Exception e) {
                // fails due to it not being a valid command and thus exception is thrown
                Exception expectedE = new InvalidCommandException(input);
                assertEquals(expectedE.getMessage(), e.getMessage()); // expects an invalid command exception
            }
        } catch (Exception e) {
            System.out.println("Exception in add to do test, test failed");
        }
    }
}
