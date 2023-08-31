package duke.task;

import duke.Duke;
import duke.DukeException;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {

    @Test
    public void addEvent_success() throws DukeException {
        String description = "Test Event /from 2023-10-20 /to 2023-10-21";
        ArrayList<Task> testList = new ArrayList<>();
        LocalDate testStart = LocalDate.parse("2023-10-20");
        LocalDate testEnd = LocalDate.parse("2023-10-21");
        Event expectedEvent = new Event("Test Event ", testStart, testEnd);
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(expectedEvent);

        assertEquals(expectedEvent, Event.addEvent(description, testList));
        assertEquals(expectedList, testList);
    }

    @Test
    public void addEvent_descriptionEmpty_exceptionThrown() {
        String description = "";
        ArrayList<Task> testList = new ArrayList<>();
        LocalDate testStart = LocalDate.parse("2023-10-20");
        LocalDate testEnd = LocalDate.parse("2023-10-21");

        try {
            assertEquals(new Event(description, testStart, testEnd), Event.addEvent(description, testList));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of an Event cannot be empty.",
                    e.getMessage());
        }
    }
}
