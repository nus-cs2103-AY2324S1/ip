package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CommandTest {
    public final String testCommand = "test";
    @Test
    public void commandValidStringTest() {
        String[] validString = { "/test 12345 6789", "/test []\\-=;':<>?." };
        String[] actualString = { "12345 6789", "[]\\-=;':<>?." };

        for (int i = 0; i < validString.length; i++) {
            assertEquals(Command.assertString(validString[i], testCommand), actualString[i]);
        }
    }

    @Test
    public void commandInvalidStringTest() {
        String[] invalidString = { "/test", "/test " };

        for (String string : invalidString) {
            assertThrows(InvalidArgumentException.class, () -> Command.assertString(string, testCommand));
        }
    }

    @Test
    public void commandValidIntegerTest() {
        String[] validInteger = { "/test 99999999", "/test 123", "/test 0" , "/test -99999999" };
        int[] testInt = { 99999999, 123, 0, -99999999 };
        for (int i = 0; i < validInteger.length; i++) {
            assertEquals(Command.assertInteger(validInteger[i], testCommand), testInt[i]);
        }
    }

    @Test
    public void commandInvalidIntegerTest() {
        String[] invalidInteger = { "/test", "/test 123 456", "/test 123.456", "/test 123]456", "/test asdgd qwe gew" };

        for (String string : invalidInteger) {
            assertThrows(InvalidArgumentException.class, () -> Command.assertInteger(string, testCommand));
        }
    }

    @Test
    public void commandValidDateTimeTest() {
        String[] validDateTimeInput = {
            "test 2022-09-07 12:34",
            "test 1999-07-22 23:59",
            "test 2040-12-31 00:00"
        };

        String[] validDateTimeString = {
            "2022-09-07T12:34:00",
            "1999-07-22T23:59:00",
            "2040-12-31T00:00:00"
        };

        for (int i = 0; i < validDateTimeInput.length; i++) {
            assertEquals(
                Command.assertDateTime(validDateTimeInput[i], testCommand)
                    .isEqual(LocalDateTime.parse(validDateTimeString[i])),
                true
            );
        }
    }

    @Test
    public void commandInvalidDateTimeTest() {
        String[] invalidDateTimeInput = {
            "test 2022-09-07 1234",
            "test 1999/07/22 23:59",
            "test 00:00 2022-09-07",
            "test 2022-13-01 00:00",
            "test 2022-04-31 00:00",
            "test 2022-05-32 00:00",
            "test 2022-09-07 24:00",
            "test 2022-09-07 12:60"
        };

        for (String string : invalidDateTimeInput) {
            assertThrows(InvalidArgumentException.class, () -> Command.assertDateTime(string, testCommand));
        }
    }
}
