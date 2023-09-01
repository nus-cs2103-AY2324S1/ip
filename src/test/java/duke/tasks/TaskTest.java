package duke.tasks;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TaskTest {
    @Test
    public void markDone_mark(){
        Task task = new Task("Sleep");
        task.markDone();
        assertTrue(task.getIsDone());
    }

    @Test
    public void unmarkDone_unmark(){
        Task task = new Task("Sleep");
        task.markDone();
        task.unmarkDone();
        assertFalse(task.getIsDone());
    }

    @Test
    public void toFileFormat_notDone_correctFormat(){
        Task task = new Task("Sleep");
        assertEquals("0 | Sleep", task.toFileFormat());
    }

    @Test
    public void toFileFormat_done_correctFormat(){
        Task task = new Task("Sleep");
        task.markDone();
        assertEquals("1 | Sleep", task.toFileFormat());
    }
}
