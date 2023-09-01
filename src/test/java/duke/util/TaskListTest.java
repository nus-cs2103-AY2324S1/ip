package duke.util;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskListTest{

    @Test
    public void delete_indexOutOfBound_nullReturned() {
        ArrayList<Task> arr = new ArrayList<>();
        TaskList tasklist = new TaskList(arr);
        Task deleted = tasklist.delete(1);
        assertNull(deleted);
    }

}
