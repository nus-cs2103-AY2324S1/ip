package chatbot.alain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chatbot.alain.tasks.Deadline;

public class DeadlinesTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Finish project", "2023-09-01 18:00");
        String expected = "[D][ ] Finish project (by: September 01 2023 18:00)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

}
