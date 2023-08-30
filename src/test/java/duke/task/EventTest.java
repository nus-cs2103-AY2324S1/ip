package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    //Initialise the event
    @Test
    public void CheckFromToEvent() {
        Exception except = assertThrows(DukeException.class,() -> {
            new Event("event project meeting /from 2019-12-08 /to 2019-12-04");
        } );

        String expectedmessage = "MEEEOOWWWWWW!!!! From is later than To!";
        String actlmessage = except.toString(); //get the full message

        assertEquals(expectedmessage, actlmessage);
    }
    @Test
    public void InitiliaseEvent() throws DukeException {
        String inputCommand = " project meeting /from 2019-12-02 /to 2019-12-04";
        String expectedName = "[E][ ] project meeting (from: Dec 2 2019 to: Dec 4 2019)";

        Event testEvent = new Event(inputCommand);
        assertEquals(expectedName, testEvent.toString());
    }

    @Test
    public void CheckPresenceOfEndDate() {
        String inputCommand = "event project meeting /from 2019-12-02";
        Exception except = assertThrows(DukeException.class,() -> {
            new Event(inputCommand);
        } );
        String expectedmessage = "MEEEOOWWWWWW!!!! Event has no end date!";
        String actlmessage = except.toString(); //get the full message
    }

    @Test
    public void CheckValidDateInput() {
        String inputCommand = " project meeting /from 2019-12-02 /to 2019-12-04";

    }


}
