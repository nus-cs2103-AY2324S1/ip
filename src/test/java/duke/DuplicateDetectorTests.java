package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duplicatedetectors.DeadlineDuplicateDetector;
import duplicatedetectors.EventDuplicateDetector;
import duplicatedetectors.TodoDuplicateDetector;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * This class tests the fucntionality of the duplicate detector classes.
 */
public class DuplicateDetectorTests {
    private final DeadlineDuplicateDetector deadlineDuplicateDetector = new DeadlineDuplicateDetector();
    private final EventDuplicateDetector eventDuplicateDetector = new EventDuplicateDetector();
    private final TodoDuplicateDetector todoDuplicateDetector = new TodoDuplicateDetector();
    private final ArrayList<Task> taskList1 = new ArrayList<>();
    private final ArrayList<Task> taskList2 = new ArrayList<>();
    private final ArrayList<Task> taskList3 = new ArrayList<>();
    private final Task todoTask = new Todo("test1");
    private final Task deadlineTask = new Deadline("test2", "2023-09-21");
    private final Task eventTask = new Event("test3", "2023-09-22", "2023-09-23");

    /**
     * This method tests the duplicate detector classes to check if they
     * correctly detect duplicates of the same type of Task.
     */
    @Test
    void testDuplicateDetectorsUsingSameType() {
        taskList1.add(todoTask);
        assertTrue(todoDuplicateDetector.checkDuplicates("test1", taskList1));
        taskList2.add(deadlineTask);
        assertTrue(deadlineDuplicateDetector.checkDuplicates("test2", "2023-09-21", taskList2));
        taskList3.add(eventTask);
        assertTrue(eventDuplicateDetector.checkDuplicates("test3", "2023-09-22",
                "2023-09-23", taskList3));
    }

    /**
     * This method tests if the duplicate detector classes to check if they
     * correct ignore the tasks of different types which have the same content (the String).
     */
    @Test
    void testDuplicateDetectorsUsingDiffTypes() {
        assertFalse(todoDuplicateDetector.checkDuplicates("test2", taskList2));
        assertFalse(eventDuplicateDetector.checkDuplicates("test2", "2023-09-22",
                "2023-09-23", taskList2));
        assertFalse(todoDuplicateDetector.checkDuplicates("test3", taskList3));
        assertFalse(deadlineDuplicateDetector.checkDuplicates("test3", "2023-09-21", taskList3));
        assertFalse(deadlineDuplicateDetector.checkDuplicates("test1", "2023-09-21", taskList1));
        assertFalse(eventDuplicateDetector.checkDuplicates("test1", "2023-09-22",
                "2023-09-23", taskList1));
    }
}
