package ballsorting;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void Test1() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-08-31 09:00", Ballsorter.inputFormatter);
        Task t = new Deadline("buy milk", dateTime);
        t.markDone();
        String expected = "[D][X] buy milk (by: 31 Aug, 09:00)";
        assertEquals(expected, t.toString());
    }
}
