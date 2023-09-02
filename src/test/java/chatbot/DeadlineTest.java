package chatbot;

import chatbot.tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void testToString() {
        Deadline deadline = new Deadline("deadline run" , "12-12-2012 19:00:00");
        String expected = "[D] [ ] deadline run (by: 12/12/2012 19:00:00)";

        String actual = deadline.toString();
        assertEquals(expected, actual);
    }
}
