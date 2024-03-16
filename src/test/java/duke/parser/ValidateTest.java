package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.error.DukeException;

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
