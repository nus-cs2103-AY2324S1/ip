package ballsorting;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void test2() {
        LocalDateTime dateTime1 = LocalDateTime.parse("2023-08-31 09:00", Ballsorter.inputFormatter);
        LocalDateTime dateTime2 = LocalDateTime.parse("2023-09-01 15:00", Ballsorter.inputFormatter);
        Task t = new Event("training", dateTime1, dateTime2);
        t.markDone();
        t.markNotDone();
        String expected = "[E][ ] training (from: 31 Aug, 09:00 to: 01 Sep, 15:00)";
        assertEquals(expected, t.toString());
    }
}
