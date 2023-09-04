package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    private LocalDateTime date = LocalDateTime.of(2023, 9, 3, 17, 00);
    @Test
    public void testLocalDateToString() {
        assertEquals("Sep 3 2023 5:00PM", Deadline.localDatetoString(date));
    }
    @Test
    public void testToString() {
        assertEquals("[D][ ] return book (by: Sep 3 2023 5:00PM)",
                new Deadline("return book ", date).toString());
    }
    @Test
    public void testToFileString() {
        assertEquals("D | O | return book | Sep 3 2023 5:00PM",
                new Deadline("return book ", date).toFileString());
    }

}
