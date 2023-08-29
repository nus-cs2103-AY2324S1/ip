package chatbot.alain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlinesTest {
    @Test
    public void testToString() {
        Deadlines deadline = new Deadlines("Finish project", "2023-09-01 18:00");
        String expected = "[D][ ] Finish project (by: 2023-09-01 18:00)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

}
