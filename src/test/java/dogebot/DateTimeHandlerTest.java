package dogebot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeHandlerTest {
    @Test
    public void testStringConversion() {
        DateTimeHandler dth = new DateTimeHandler("6/9/2023 1523");
        assertEquals("06 Sep 2023 3:23PM", dth.toString());
    }
}
