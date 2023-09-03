package robert.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskListTest {
    private final ArrayList<Task> sampleTaskArrayList = new ArrayList<>(
            Arrays.asList(new TaskStub(), new TaskStub(), new TaskStub()));

    @Test
    public void testGetters() {

        try {
            assertEquals("[X] TASKSTUB",
                    new TaskList(this.sampleTaskArrayList).getTask(1).toString());

            assertEquals(3,
                    new TaskList(this.sampleTaskArrayList).getTaskCount());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void testDeletion_indexInBound_success() {
        try {
            assertEquals("[X] TASKSTUB",
                    new TaskList(this.sampleTaskArrayList).deleteTask(1).toString());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void testDeletion_indexOutOfBound_exceptionThrown() {
        try {
            assertEquals("[X] TASKSTUB",
                    new TaskList(this.sampleTaskArrayList).deleteTask(10).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! Index is out of bounds.\n"
                    + "Please choose a valid index.", e.toString());
        }
    }
}
