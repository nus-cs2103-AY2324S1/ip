package qi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringDescription() {
        assertEquals("[D][ ] return book (by: Sep 22 2023)",
                new Deadline("return book", LocalDate.parse("2023-09-22")).toString());
    }
}
