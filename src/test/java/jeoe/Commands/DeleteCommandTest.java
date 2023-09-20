package jeoe.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeoe.Exceptions.IndexOutOfBoundsException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

public class DeleteCommandTest {
    protected StorageManager sm;
    protected TaskManager tm;
    protected Ui ui = new Ui();

    @Test
    public void testDeleteTask() {
        try {
            String filePath = System.getProperty("user.dir") + "/storage/testTaskListData.txt";
            sm = new StorageManager(filePath);
            sm.deleteAllInFile();
            tm = new TaskManager(sm.load(), filePath);
            TodoCommand todoTest = new TodoCommand("todo test");
            todoTest.execute(tm, ui, sm);
            DeleteCommand deleteCommand = new DeleteCommand("delete 1");
            deleteCommand.executeAndReply(tm, ui, sm);
            assertEquals("", tm.toString());
        } catch (Exception e) {
            System.out.println("Exception in add delete test, test failed");
        }
    }

    @Test
    public void testFailedDelete() {
        try {
            String filePath = System.getProperty("user.dir") + "/storage/testTaskListData.txt";
            sm = new StorageManager(filePath);
            sm.deleteAllInFile();
            tm = new TaskManager(sm.load(), filePath);
            TodoCommand todoTest = new TodoCommand("todo test");
            todoTest.execute(tm, ui, sm);

            // will be parsed, but index is out of bound
            Command deleteCommand = CommandParser.parse("delete 2");
            String reply = deleteCommand.executeAndReply(tm, ui, sm);
            Exception e = new IndexOutOfBoundsException("2");
            String errorMsg = ui.getReply(e.getMessage());
            assertEquals(errorMsg, reply); // expects an index out of bounds exception
        } catch (Exception e) {
            System.out.println("Exception in add delete test, test failed");
        }
    }
}
