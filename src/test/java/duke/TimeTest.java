package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class TimeTest {

    @Test
    public void parseDateTime_validDateTime_success() {
        try {
            LocalDateTime dateTime = Time.parseDateTime("1/1/2023 18:00");
            assert dateTime.equals(LocalDateTime.of(2023, 1, 1, 18, 0));

            dateTime = Time.parseDateTime("1/10/2023 18:02");
            assert dateTime.equals(LocalDateTime.of(2023, 10, 1, 18, 2));

            dateTime = Time.parseDateTime("10/1/2023 08:00");
            assert dateTime.equals(LocalDateTime.of(2023, 1, 10, 8, 0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseDateTime_invalidDateTime_exceptionThrown() {
        try {
            Time.parseDateTime("10/1/2023 8:0");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Time.parseDateTime("10/13/2023 13:00");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Time.parseDateTime("32/12/2023 13:00");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Time.parseDateTime("ABCDEF");
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void parseDate_validDate_success() {
        try {
            LocalDate date = Time.parseDate("1/1/2023");
            LocalDate date2 = Time.parseDate("31/3/2023");
            assert date.equals(LocalDate.of(2023, 1, 1));
            assert date2.equals(LocalDate.of(2023, 3, 31));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        try {
            Time.parseDate("1/1/2023 18:00");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Time.parseDate("ABCDEF");
            fail();
        } catch (DukeException e) {
            assert true;
        }

        try {
            Time.parseDate("32/3/2023");
            fail();
        } catch (DukeException e) {
            assert true;
        }
    }
}
