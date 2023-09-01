package blip.parse;

import blip.parser.DateConverter;
import blip.exceptions.DateTimeFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
public class DateConverterTest {
    @Test
    public void convertDateTimeTest_validDateTime_success() throws DateTimeFormatException{
        assertEquals(LocalDateTime.of(2023,10,3,10,20)
                ,DateConverter.convertToDateTime("2023-10-03 10:20"));
        assertEquals(LocalDateTime.of(2012,12,12,12,12)
                ,DateConverter.convertToDateTime("2012-12-12 12:12"));
    }

    @Test
    public void convertDateTimeTest_throwsException_success() throws DateTimeFormatException{
        try {
            assertEquals(LocalDateTime.of(2023,10,3,10,20)
                    ,DateConverter.convertToDateTime("2023 10 03 10 20"));
            fail();
        } catch (Exception e) {
            assertEquals("Error with date time format: ", e.getMessage());
        }
    }

}
