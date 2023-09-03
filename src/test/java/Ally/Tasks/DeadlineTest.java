package Ally.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void getStatusIcon_markAsDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void getStatusIcon_markAsNotDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkNotDone();
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void markAsDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkDone();
        assertTrue(deadline.isDone);
    }

    @Test
    public void markAsNotDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkNotDone();
        assertFalse(deadline.isDone);
    }

    @Test
    public void formatFile_markAsUndone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkNotDone();
        String expected = "D | 0 | Sleep 2020-02-02T18:00:00";
        assertEquals(expected, deadline.formatFile());
    }

    @Test
    public void formatFile_markAsDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkDone();
        String expected = "D | 1 | Sleep 2020-02-02T18:00:00";
        assertEquals(expected, deadline.formatFile());
    }

    @Test
    public void toString_markAsUndone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkNotDone();
        String expected = "[D][ ] Sleep (by: Feb 02 2020 6pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_markAsDone_success(){
        Deadline deadline = new Deadline("Sleep", "by 2020-02-02 1800");
        deadline.setMarkDone();
        String expected = "[D][X] Sleep (by: Feb 02 2020 6pm)";
        assertEquals(expected, deadline.toString());
    }
}
