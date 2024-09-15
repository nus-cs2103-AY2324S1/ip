package emiya.datehandler;

import emiya.emiyaexception.InvalidDateTimeException;
import emiya.emiyaexception.WrongDateTimeFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static emiya.datehandler.DateHandler.determineDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateHandlerTest {
    @Test
    public void determineDateTime_correctDateTimeFormat_dateTimeObjectsEqual(){
        LocalDateTime testObj;
        String input = "2023-09-01 1200";
        try {
            testObj = determineDateTime(input);
        } catch (WrongDateTimeFormatException | InvalidDateTimeException e ) {
            throw new RuntimeException(e);
        }
        assertEquals(LocalDateTime.of(2023, 9,1,12,0), testObj);
    }

    @Test
    public void determineDateTime_wrongDateFormat_exceptionThrown(){
        String input = "2023/09/01 1200";
        try {
            LocalDateTime result = determineDateTime(input);
            fail("Expected WrongDateTimeFormatException to be thrown");
        } catch (WrongDateTimeFormatException e) {
            // Test passes
        } catch (InvalidDateTimeException e) {
            fail("Unexpected InvalidDateException thrown");
        }
    }

    @Test
    public void determineDateTime_wrongTimeFormat_exceptionThrown(){
        String input = "2023-09-01 2.00pm";
        try {
            LocalDateTime result = determineDateTime(input);
            fail("Expected WrongDateTimeFormatException to be thrown");
        } catch (WrongDateTimeFormatException e) {
            // Test passes
        } catch (InvalidDateTimeException e) {
            fail("Unexpected InvalidDateException thrown");
        }
    }

    @Test
    public void determineDateTime_invalidTimeGiven_exceptionThrown(){
        String input = "2023-09-01 1793";
        try {
            LocalDateTime result = determineDateTime(input);
            fail("Expected InvalidDateException to be thrown");
        } catch (WrongDateTimeFormatException e) {
            fail("Unexpected WrongDateTimeFormatException thrown");
        } catch (InvalidDateTimeException e) {
            // Test passes
        }
    }
}
