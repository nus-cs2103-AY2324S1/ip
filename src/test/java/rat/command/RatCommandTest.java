package rat.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

public class RatCommandTest {

    @Test
    public void validateTime_validTime_success() {
        try {
            assertTrue(RatCommand.validateTime("01/01/2020 00:00"));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void validateTime_invalidTime_exceptionThrown() {
        try {
            assertFalse(RatCommand.validateTime("01 Jan 2023"));
        } catch (ParseException e) {
            assertThrows(ParseException.class, () -> {
                throw e;
            });
        }
    }
}
