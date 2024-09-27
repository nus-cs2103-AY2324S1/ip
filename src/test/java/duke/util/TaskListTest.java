package duke.util;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests the TaskList Class used in CheeChat
 */
public class TaskListTest{

    /**
     * Tests whether the delete function in the TaskList class will return a null value
     * when the input index if larger than the size of the TaskList.
     */
    @Test
    public void delete_indexOutOfBound_nullReturned() {
        ArrayList<Task> arr = new ArrayList<>();
        TaskList tasklist = new TaskList(arr);
        Task deleted = tasklist.delete(1);
        assertNull(deleted);
    }

}
