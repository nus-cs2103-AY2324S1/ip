package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.Storage;

public class EventTest {
    @Test
    public void testEventStringConversion() throws DukeException {
        assertEquals(
                "[E][ ] Malaysia Trip (from: Aug 12 2023 to: Aug 13 2023)",
                new Event("Malaysia Trip", "2023-08-12", "2023-08-13").toString()
        );

        assertEquals(
                "[E][ ] Camp (from: Oct 11 2023 to: Oct 14 2023)",
                new Event("Camp", "2023-10-11", "2023-10-14").toString()
        );

        assertEquals(
                "[E][ ] Birthday Party (from: Oct 15 2023 to: Oct 16 2023)",
                new Event("Birthday Party", "2023-10-15", "2023-10-16").toString()
        );
    }

    @Test
    public void testConstructor_invalidDates_exceptionThrown() {
        try {
            assertEquals(
                    "[E][ ] Graduation Trip (from: May 10 2024 to: May 9 2024)",
                    new Event("Graduation Trip", "2024-05-10", "2024-05-09").toString()
            );
        } catch (Exception e) {
            assertEquals("Start date cannot be after end date", e.getMessage());
        }
    }

    @Test
    public void testEventReschedule() throws DukeException {
        assertEquals(
                "Rescheduled Task: [E][ ] Stay-cation (from: Oct 15 2023 to: Oct 16 2023)",
                new Event("Stay-cation", "2023-10-14", "2023-10-15")
                        .reschedule(
                                "/from 2023-10-15 /to 2023-10-16",
                                new Storage("./data/tasks.txt")
                        )
        );

        assertEquals(
                "Rescheduled Task: [E][ ] Hackathon (from: Dec 22 2023 to: Dec 24 2023)",
                new Event("Hackathon", "2023-08-22", "2023-08-24")
                        .reschedule(
                                "/from 2023-12-22 /to 2023-12-24",
                                new Storage("./data/tasks.txt")
                        )
        );
    }

    @Test
    public void testEventReschedule_invalidFormat_exceptionThrown() {
        try {
            assertEquals(
                    "[E][ ] Sleepover (/from 2023-11-11 /to 2023-11-12)",
                    new Event("Sleepover", "2023-11-01", "2023-11-03")
                            .reschedule(
                                    "2023-11-11 2023-11-12",
                                    new Storage("./data/tasks.txt")
                            )
            );
        } catch (Exception e) {
            assertEquals(
                    "Valid Input Syntax: reschedule {taskNumber} /from {newTime} /to {newTime}",
                    e.getMessage()
            );
        }
    }

}
