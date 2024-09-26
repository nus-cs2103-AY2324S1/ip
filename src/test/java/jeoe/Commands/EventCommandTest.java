package jeoe.Commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

public class EventCommandTest {
    protected StorageManager sm;
    protected TaskManager tm;
    protected Ui ui = new Ui();

    @Test
    public void testAddEvent() {
        try {
            String filePath = System.getProperty("user.dir") + "/storage/testTaskListData.txt";
            sm = new StorageManager(filePath);
            sm.deleteAllInFile();
            tm = new TaskManager(sm.load(), filePath);
            EventCommand event = new EventCommand("event birthday /from yesterday /to today");
            event.executeAndReply(tm, ui, sm);
            assertEquals("1. [E][ ] birthday (from: yesterday to: today)\n", tm.toString());
        } catch (Exception e) {
            System.out.println("Exception in add event test, test failed");
        }
    }
}
