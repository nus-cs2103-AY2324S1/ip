package ballsorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testMarkDone() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-08-31 09:00", Ballsorter.inputFormatter);
        Task t = new Deadline("buy milk", dateTime);
        t.markDone();
        String expected = "[D][X] buy milk (by: 31 Aug, 09:00)";
        assertEquals(expected, t.toString());
    }
}
