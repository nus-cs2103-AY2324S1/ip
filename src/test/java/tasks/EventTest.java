package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    public void eventToStringTest(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        String expected = "[E][ ] bootcamp (from: 12.10.2023 to: 15.10.2023)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void eventToStringTest_marked(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        e.isComplete = true;
        String expected = "[E][X] bootcamp (from: 12.10.2023 to: 15.10.2023)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void eventToStringStoreTest(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        String expected = "E-O-bootcamp-12 Oct 2023-15 Oct 2023";
        assertEquals(expected, e.taskToStringStore(e));
    }
    @Test
    public void deadlineToStringStoreTest_marked(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        e.isComplete = true;
        String expected = "E-X-bootcamp-12 Oct 2023-15 Oct 2023";
        assertEquals(expected, e.taskToStringStore(e));
    }


    @Test
    public void deadlineGetMark_unmarked(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        String expected = " ";
        assertEquals(expected, e.getMark());
    }

    @Test
    public void deadlineGetMark_marked(){
        Event e = new Event("bootcamp", "12 Oct 2023", "15 Oct 2023");
        e.isComplete = true;
        String expected = "X";
        assertEquals(expected, e.getMark());
    }

}
