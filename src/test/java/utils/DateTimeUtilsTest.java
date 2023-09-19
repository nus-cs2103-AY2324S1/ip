package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateTimeException;
import duke.utils.DateTimeUtils;




public class DateTimeUtilsTest {

    @Test
    public void testStringToLocalDateTime() throws InvalidDateTimeException {
        // Test case 1: Using format M/d/yyyy HHmm
        String testString1 = "8/31/2023 1430";
        assertEquals(LocalDateTime.of(2023, 8, 31, 14, 30), DateTimeUtils.stringToLocalDateTime(testString1));

        // Test case 2: Using format M/d/yyyy
        String testString2 = "8/31/2023";
        assertEquals(LocalDateTime.of(2023, 8, 31, 0, 0), DateTimeUtils.stringToLocalDateTime(testString2));

        // Test case 3: Using format yyyy-MM-dd
        String testString3 = "2023-08-31";
        assertEquals(LocalDateTime.of(2023, 8, 31, 0, 0), DateTimeUtils.stringToLocalDateTime(testString3));

        // Test case 4: Using format yyyy-MM-dd HHmm
        String testString4 = "2023-08-31 1430";
        assertEquals(LocalDateTime.of(2023, 8, 31, 14, 30), DateTimeUtils.stringToLocalDateTime(testString4));
    }

    @Test
    public void testInvalidStringToLocalDateTime() {
        // Test case 1: Invalid format
        String testString1 = "31 Aug 2023";
        assertThrows(InvalidDateTimeException.class, () -> DateTimeUtils.stringToLocalDateTime(testString1));

        // Test case 2: Invalid date
        String testString2 = "8/32/2023";
        assertThrows(InvalidDateTimeException.class, () -> DateTimeUtils.stringToLocalDateTime(testString2));
    }

}
