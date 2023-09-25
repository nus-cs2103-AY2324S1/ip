package dogebot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTimeHandlerTest {
    @Test
    public void testStringConversion() {
        DateTimeHandler test1 = new DateTimeHandler("6/9/2023 1523");
        assertEquals("06 Sep 2023 3:23PM", test1.toString());

        DateTimeHandler test2 = new DateTimeHandler("5/10/2023 0830");
        assertEquals("05 Oct 2023 8:30AM", test2.toString());
    }
}
