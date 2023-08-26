package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import duke.error.DukeException;

import java.time.LocalDateTime;

public class ValidateTest {

    @Test
    public void testValidateLocalDateTimeValid() {
        try {
            LocalDateTime dateTime = Validate.validateLocalDateTime("2023-08-26 15:30");
            assertNotNull(dateTime);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid date-time format");
        }
    }

    @Test
    public void testValidateLocalDateTimeInvalid() {
        assertThrows(DukeException.class, () -> {
            Validate.validateLocalDateTime("2023-08-26");
        });
    }

    @Test
    public void testValidateNumberValid() {
        try {
            int number = Validate.validateNumber("42");
            assertEquals(42, number);
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid number format");
        }
    }

    @Test
    public void testValidateNumberInvalid() {
        assertThrows(DukeException.class, () -> {
            Validate.validateNumber("invalid");
        });
    }
}
