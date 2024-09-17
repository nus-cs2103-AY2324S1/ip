package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.Todo;
import workers.DeleteWorker;
import workers.MarkWorker;

/**
 * This class tests the worker classes that rely on indexes.
 */
public class WorkersIndexOutOfBoundsTests {
    private final ArrayList<Task> testTaskList = new ArrayList<>();
    private final DeleteWorker deleteWorker = new DeleteWorker();
    private final MarkWorker markWorker = new MarkWorker();
    private final Task todoTask = new Todo("test1");
    private final String[] testInputPartsZero = new String[] {"random", "0"};
    private final String[] testInputPartsAboveOne = new String[] {"random", "10"};

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
