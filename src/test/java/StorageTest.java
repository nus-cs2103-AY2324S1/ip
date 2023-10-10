import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    public void testSaveTasks() {
        TaskList originalList = new TaskList();
        Storage.load(originalList);

        TaskList testList = new TaskList();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        testList.addTask(task1);
        testList.addTask(task2);
        Storage.saveTasks(testList);

        TaskList verifyList = new TaskList();
        Storage.load(verifyList);
        assertEquals(verifyList.getTask(0), task1);
        assertEquals(verifyList.getTask(1), task2);
    }
}
