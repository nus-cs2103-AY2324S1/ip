package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void test_Valid_DateTime() {
        String validDateTime = "2023-09-02 12:30";
        LocalDateTime result = Parser.dateFormatter(validDateTime);
        assertNotNull(result);
    }

    @Test
    public void test_InvalidDate_ValidTime() {
        String InvalidDateValidTime = "2020-13-13 12:30";
        assertThrows(DateTimeParseException.class, () -> Parser.dateFormatter(InvalidDateValidTime));
    }

    @Test
    public void test_ValidDate_InvalidTime() {
        String ValidDateInvalidTime = "2020-12-13 40:10";
        assertThrows(DateTimeParseException.class, () -> Parser.dateFormatter(ValidDateInvalidTime));
    }
}