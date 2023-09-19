package cyrus.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateUtilityTest {
    @Test
    public void testParseWithValidString() {
        var validInput = "12/12/2022";
        var expectedLocalDate = LocalDate.of(2022, 12, 12);
        var inputDate = DateUtility.parse(validInput);
        assertEquals(expectedLocalDate, inputDate);
    }

    @Test
    public void testParseWithInvalidString() {
        var validInput = "12 December 2023";
        var inputDate = DateUtility.parse(validInput);
        assertNull(inputDate);
    }

    @Test
    public void testFormatLocalDateToReadableFormat() {
        var input = LocalDate.of(2025, 9, 15);
        var result = DateUtility.formatLocalDate(input);
        assertEquals("15 September 2025", result);
    }

    @Test
    public void testToInputFormatToConvertLocalDateToInputForm() {
        var input = LocalDate.of(2025, 9, 15);
        var result = DateUtility.toInputFormat(input);
        assertEquals("15/09/2025", result);
    }

    @Test
    public void testToInputFormatWithNullDateReturnsNull() {
        var result = DateUtility.toInputFormat(null);
        assertNull(result);
    }
}
