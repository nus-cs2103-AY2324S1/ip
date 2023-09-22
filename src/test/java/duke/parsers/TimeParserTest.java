package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exceptions.UnknownCommandException;

public class TimeParserTest {

    @Test
    public void parseToLocalDate_validDate_success() throws UnknownCommandException {
        LocalDate result = TimeParser.parseToLocalDate("2023-09-20");
        assertEquals(LocalDate.of(2023, 9, 20), result);
    }

    @Test
    public void parseToLocalDate_invalidDate_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> TimeParser.parseToLocalDate("invalid-date"));
    }

    @Test
    public void parseToLocalDateTime_validDateTime_success() throws UnknownCommandException {
        LocalDateTime result = TimeParser.parseToLocalDateTime("2023-09-20T14:30");
        assertEquals(LocalDateTime.of(2023, 9, 20, 14, 30), result);
    }

    @Test
    public void parseToLocalDateTime_invalidDateTime_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> TimeParser.parseToLocalDateTime("invalid-datetime"));
    }
}
