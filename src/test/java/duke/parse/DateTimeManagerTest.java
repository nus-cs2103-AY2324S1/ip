package duke.parse;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeManagerTest {
    private void notifyError() {
        fail("should not throw exception");
    }

    @Test
    public void parseInputTest() {
        try {
            assertEquals(
                    LocalDateTime.of(
                            LocalDate.now(),
                            LocalTime.of(22, 13)
                    ),
                    DateTimeManager.inputToDate("today 22:13")
            );
            assertEquals(
                    LocalDateTime.of(
                            LocalDate.now(),
                            LocalTime.of(22, 13)
                    ),
                    DateTimeManager.inputToDate("today 10:13pm")
            );
            assertEquals(
                    LocalDateTime.of(
                            LocalDate.of(2023, 12, 20),
                            LocalTime.of(12, 13)
                    ),
                    DateTimeManager.inputToDate("20/12/2023 12:13pm")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.inputToDate("today 13pm")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.inputToDate("30/2/2024 10am")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.inputToDate("10/2/2024 10:70am")
            );
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            notifyError();
        }
    }

    @Test
    public void parseDateTest() {
        try {
            assertEquals(
                    LocalDate.now(),
                    DateTimeManager.parseDate("today")
            );
            assertEquals(
                    LocalDate.now().plusDays(1),
                    DateTimeManager.parseDate("tmr")
            );
            assertEquals(
                    LocalDate.of(2023, 9, 10),
                    DateTimeManager.parseDate("10/9/2023")
            );
            assertEquals(
                    LocalDate.of(2023, 1, 1),
                    DateTimeManager.parseDate("1/1/2023")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.parseDate("31/9/2023")
            );
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            notifyError();
        }
    }

    @Test
    public void parseTimeTest() {
        try {
            assertEquals(
                    LocalTime.of(22, 0),
                    DateTimeManager.parseTime("10pm")
            );
            assertEquals(
                    LocalTime.of(16, 33),
                    DateTimeManager.parseTime("4:33pm")
            );
            assertEquals(
                    LocalTime.of(16, 33),
                    DateTimeManager.parseTime("16:33")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.parseTime("24:13")
            );
            assertThrows(
                    DateTimeManager.DateParseException.class,
                    () -> DateTimeManager.parseTime("15:00pm")
            );
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            notifyError();
        }
    }

    @Test
    public void dateToStringDataTest() {
        assertEquals(
                "12/9/2023 12:0",
                DateTimeManager.dateToStringData(LocalDateTime.of(2023, 9, 12, 12, 0))
        );
        assertEquals(
                "12/9/2023 14:27",
                DateTimeManager.dateToStringData(LocalDateTime.of(2023, 9, 12, 14, 27))
        );
        assertEquals(
                "1/10/2023 0:0",
                DateTimeManager.dateToStringData(LocalDateTime.of(2023, 10, 1, 0, 0))
        );
    }

    @Test
    public void dateDisplayTest() {
        assertEquals(
                "today 10:11am",
                DateTimeManager.dateToDisplay(
                        LocalDateTime.of(
                                LocalDate.now(),
                                LocalTime.of(10, 11)
                        )
                )
        );
        assertEquals(
                "tomorrow 12:13am",
                DateTimeManager.dateToDisplay(
                        LocalDateTime.of(
                                LocalDate.now(),
                                LocalTime.of(0, 13)
                        ).plusDays(1)
                )
        );
        assertEquals(
                "5/9/2023 12:07pm",
                DateTimeManager.dateToDisplay(
                        LocalDateTime.of(2023, 9, 5, 12, 7)
                )
        );
        assertEquals(
                "2/9/2023 02:00pm",
                DateTimeManager.dateToDisplay(
                        LocalDateTime.of(2023, 9, 2, 14, 0)
                )
        );
    }
}
