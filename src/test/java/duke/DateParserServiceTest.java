package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateParserServiceTest {

    @Test
    public void testValidDateFormats() {
        // Test various valid date formats
        String[] validDates = {
            "2021-08-30", "30/08/2021", "30-08-2021",
            "Aug 30 2021", "30 Aug 2021", "2021/08/30"
        };

        LocalDate expectedDate = LocalDate.of(2021, 8, 30);

        for (String date : validDates) {
            LocalDate parsedDate = DateParserService.parseDate(date);
            assertEquals(expectedDate, parsedDate);
        }
    }

    @Test
    public void testInvalidDateFormats() {
        // Test invalid date formats
        String[] invalidDates = {
            "2021-13-30", "30/08/21", "30-08-21",
            "August 30 2021", "30 August", "2021/30/08"
        };

        for (String date : invalidDates) {
            assertThrows(DateTimeParseException.class, () -> DateParserService.parseDate(date));
        }
    }
}
