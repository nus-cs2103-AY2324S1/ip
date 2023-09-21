package bellcurvegod.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void generateEventFromInput_emptyDescription_exceptionThrown() {
        try {
            Event.generateEventFromInput("event");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide any description to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n", e.getMessage());
        }
    }

    @Test
    public void generateEventFromInput_emptyStart_exceptionThrown() {
        try {
            Event.generateEventFromInput("event meeting");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide a from date to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n", e.getMessage());
        }
    }

    @Test
    public void generateEventFromInput_emptyEnd_exceptionThrown() {
        try {
            Event.generateEventFromInput("event meeting /from 2023-09-09");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide a to date to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n", e.getMessage());
        }
    }

    @Test
    public void generateEventFromInput_earlierStartThanEnd_exceptionThrown() {
        try {
            Event.generateEventFromInput("event meeting /from 2023-09-09 /to 2023-09-07");
            fail();
        } catch (Exception e) {
            assertEquals("The to time entered is earlier than the from time."
                + "Please check your input.\n", e.getMessage());
        }
    }
}
