package duke;

import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Todo;
import workers.DeleteWorker;
import workers.MarkWorker;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the worker classes that rely on indexes.
 */
public class WorkersIndexOutOfBoundsTests {
    ArrayList<Task> testTaskList = new ArrayList<>();
    DeleteWorker deleteWorker = new DeleteWorker();
    MarkWorker markWorker = new MarkWorker();
    Task todoTask = new Todo("test1");
    String[] testInputPartsZero = new String[] {"random", "0"};
    String[] testInputPartsAboveOne = new String[] {"random", "10"};

    /**
     * This method tests if the methods return the right error messages when given an index
     * that is 0 or below and above the size of the current tasklist.
     */
    @Test
    public void testWorkerClasses() {
        testTaskList.add(todoTask);
        assertEquals("☹ OOPS!!! Unable to delete non-existent task",
                deleteWorker.work(testInputPartsZero, testTaskList));
        assertEquals("☹ OOPS!!! Unable to delete non-existent task",
                deleteWorker.work(testInputPartsAboveOne, testTaskList));
        assertEquals("Task does not exist. Please enter a valid index number.",
                markWorker.work(testInputPartsZero, testTaskList, true));
        assertEquals("Task does not exist. Please enter a valid index number.",
                markWorker.work(testInputPartsAboveOne, testTaskList, true));
        assertEquals("Task does not exist. Please enter a valid index number.",
                markWorker.work(testInputPartsZero, testTaskList, false));
        assertEquals("Task does not exist. Please enter a valid index number.",
                markWorker.work(testInputPartsAboveOne, testTaskList, false));
    }
}
