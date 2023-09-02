package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exception.TimeUtilException;

public class TimeUtilTest {
    @Test
    public void testParseDateTimeString_withDateTimeInputs() {
        List<String> dateTimeInputs = List.of(
                "2023-09-28 1800",
                "20230928 1800"
        );
        LocalDateTime expected = LocalDateTime.of(2023, 9, 28, 18, 0);
        for (String input : dateTimeInputs) {
            try {
                LocalDateTime actual = TimeUtil.parseDateTimeString(input);
                assertEquals(expected, actual);
            } catch (TimeUtilException e) {
                fail();
            }
        }
    }

    @Test
    public void testParseDateTimeString_withDateOnlyInputs() {
        LocalDateTime now = LocalDateTime.now();
        List<String> dateOnlyInputs = List.of(
                "2023-09-28",
                "20230928",
                "28 September 2023",
                "28 Sep 2023"
        );
        LocalDateTime expected = LocalDateTime.of(2023, 9, 28, now.getHour(), now.getMinute());
        for (String input : dateOnlyInputs) {
            try {
                LocalDateTime actual = TimeUtil.parseDateTimeString(input);
                assertTrue(isWithinOneMinuteOfExpected(expected, actual));
            } catch (TimeUtilException e) {
                fail();
            }
        }
    }

    @Test
    public void testParseDateTimeString_withSpecialInputs() {
        List<String> specialInputs = List.of("today", "tomorrow");
        List<LocalDateTime> expected = List.of(
                LocalDateTime.now().withHour(23).withMinute(59),
                LocalDateTime.now().plusDays(1).withHour(23).withMinute(59)
        );
        for (int i = 0; i < specialInputs.size(); ++i) {
            try {
                LocalDateTime actual = TimeUtil.parseDateTimeString(specialInputs.get(i));
                assertTrue(isWithinOneMinuteOfExpected(expected.get(i), actual));
            } catch (TimeUtilException e) {
                fail();
            }
        }
    }

    @Test
    public void testParseDateTimeString_withInvalidInputs() {
        List<String> invalidInputs = List.of(
                "", // empty
                "abc", // non-numeric
                "2023-13-25", // invalid month
                "2023-09-32", // invalid day
                "2021-02-29", // 29 Feb on a non-leap year.
                "2023090", // incomplete string
                "15 Febrary 2023" // mispelt month
        );
        for (String input : invalidInputs) {
            assertThrows(TimeUtilException.class, () -> TimeUtil.parseDateTimeString(input));
        }
    }

    private boolean isWithinOneMinuteOfExpected(LocalDateTime expected, LocalDateTime actual) {
        LocalDateTime oneMinuteAgo = expected.minusMinutes(1);
        LocalDateTime oneMinuteFromNow = expected.plusMinutes(1);

        return actual.isAfter(oneMinuteAgo) && actual.isBefore(oneMinuteFromNow);
    }
}
