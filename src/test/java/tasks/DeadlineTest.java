package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        String expected = "[D][ ] submit project (by: 12.10.2023)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void deadlineToStringTest_marked(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        d.isComplete = true;
        String expected = "[D][X] submit project (by: 12.10.2023)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void deadlineToStringStoreTest(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        String expected = "D-O-submit project-12 Oct 2023";
        assertEquals(expected, d.taskToStringStore(d));
    }
    @Test
    public void deadlineToStringStoreTest_marked(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        d.isComplete = true;
        String expected = "D-X-submit project-12 Oct 2023";
        assertEquals(expected, d.taskToStringStore(d));
    }


    @Test
    public void deadlineGetMark_unmarked(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        String expected = " ";
        assertEquals(expected, d.getMark());
    }

    @Test
    public void deadlineGetMark_marked(){
        Deadline d = new Deadline("submit project", "12 Oct 2023");
        d.isComplete = true;
        String expected = "X";
        assertEquals(expected, d.getMark());
    }



}
