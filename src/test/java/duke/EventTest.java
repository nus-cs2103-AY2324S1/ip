package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    @Test
    public void testEventDisplayNotDone() {
        Event event = new Event("Conference", "2023-08-25", "2023-08-27");

        String expected = "[E][] Conference (From: Aug 25 2023 To: Aug 27 2023)";
        String result = event.display();

        assertEquals(expected, result, "Event display test (not done) failed: incorrect result");
    }

    @Test
    public void testEventDisplayDone() {
        Event event = new Event("Party", "2023-08-30", "2023-09-01");
        event.done = true;

        String expected = "[E][X] Party (From: Aug 30 2023 To: Sep 01 2023)";
        String result = event.display();

        assertEquals(expected, result, "Event display test (done) failed: incorrect result");
    }

    @Test
    public void testEventDisplayWithDifferentDateFormat() {
        Event event = new Event("Meeting", "2023-12-15", "2023-12-20");
        event.done = true;

        String expected = "[E][X] Meeting (From: Dec 15 2023 To: Dec 20 2023)";
        String result = event.display();

        assertEquals(expected, result, "Event display test (different date format) failed: incorrect result");
    }

    @Test
    public void testEventDisplay2() {
        Event event = new Event("Workshop", "2023-11-05", "2023-11-07");

        String expected = "[E][] Workshop (From: Nov 05 2023 To: Nov 07 2023)";
        String result = event.display();

        assertEquals(expected, result, "Event display test failed: incorrect result");
    }

}

