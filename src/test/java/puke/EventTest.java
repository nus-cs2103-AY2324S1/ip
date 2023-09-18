package puke;

import org.junit.jupiter.api.Test;
import puke.managers.PukeException;
import puke.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTests() throws PukeException {
        String[] tags1 = new String[1];
        tags1[0] = "ugly";
        String objectInput = "project meeting /from 2023-08-30T14:00/to 2023-08-30T16:00";
        String[] objectSplit = objectInput.split("/");
        Event testObject = new Event(objectSplit, tags1);

        //Testing Write
        assertEquals("[E]/0/project meeting /2023-08-30T14:00/2023-08-30T16:00/ugly", testObject.write());
        //Testing toString()
        assertEquals("[E][ ] project meeting  (from: 2023-08-30T14:00 to: 2023-08-30T16:00) #ugly ", testObject.toString());
    }
}
