package pippi.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
public class DateFormatterTest {
    @Test
    public void testConvertToLocalDate() {
        String inputDate = "2023-08-31";
        LocalDate expectedDate = LocalDate.of(2023, 8, 31);

        LocalDate result = DateFormatter.convertToLocalDate(inputDate);

        assertEquals(expectedDate, result);
    }

    @Test
    public void testConvertDateToString() {
        LocalDate inputDate = LocalDate.of(2023, 8, 31);
        String expectedString = "Aug 31 2023";

        String result = DateFormatter.convertDateToString(inputDate);

        assertEquals(expectedString, result);
    }

    @Test
    public void testIsValidFormatValidDate() {
        String validDate = "2023-08-31";

        boolean isValid = DateFormatter.isValidFormat(validDate);

        assertTrue(isValid);
    }

    @Test
    public void testIsValidFormatInvalidDate() {
        String invalidDate = "2023-13-31"; // Invalid month (13)

        boolean isValid = DateFormatter.isValidFormat(invalidDate);

        assertFalse(isValid);
    }
}
