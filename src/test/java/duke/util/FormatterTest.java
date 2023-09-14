package duke.util;

import static duke.util.Formatter.formatDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class FormatterTest {
    @Test
    public void testFormatDateTime() {
        LocalDateTime d = LocalDateTime.parse("2023-11-15T12:34:56");
        assertEquals(formatDateTime(d), "Nov 15 2023, 12:34:56");

        LocalDateTime now = LocalDateTime.now();
        assertEquals(formatDateTime(now),
                now.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")));
    }
}
