package ekud.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeTest {
    @Test
    public void parse_validDateTime() {
        DateTime dateTime = DateTime.parse("03-04-2023 17:53");
        LocalDate localDate = dateTime.getDate();
        LocalTime localTime = dateTime.getTime();
        LocalDateTime localDateTime = dateTime.getDateTime();
        assertEquals(localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")), "3 Apr 2023");
        assertEquals(localTime.format(DateTimeFormatter.ofPattern("HH:mm")), "17:53");
        assertEquals(localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")), "3 Apr 2023 17:53");
    }

    @Test
    public void parse_validDate() {
        DateTime dateTime = DateTime.parse("03-04-2023");
        LocalDate localDate = dateTime.getDate();
        LocalTime localTime = dateTime.getTime();
        LocalDateTime localDateTime = dateTime.getDateTime();
        assertEquals(localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")), "3 Apr 2023");
        assertEquals(localTime, null);
        assertEquals(localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")), "3 Apr 2023");
    }

    @Test
    public void parse_validTime() {
        DateTime dateTime = DateTime.parse("17:53");
        LocalDate localDate = dateTime.getDate();
        LocalTime localTime = dateTime.getTime();
        LocalDateTime localDateTime = dateTime.getDateTime();
        assertEquals(localDate, null);
        assertEquals(localTime.format(DateTimeFormatter.ofPattern("HH:mm")), "17:53");
        assertEquals(localDateTime.format(DateTimeFormatter.ofPattern("HH:mm")), "17:53");
    }

    @Test
    public void parse_invalidDateTime() {
        assertThrows(DateTimeParseException.class, () -> DateTime.parse("invalid date 17:42"));
        assertThrows(DateTimeParseException.class, () -> DateTime.parse("03-04-2023 invalid time"));
    }
}
