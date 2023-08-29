package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_wrongDateFormat_exceptionThrown() {
        try {
            new Deadline("task", "wrong date");
            fail(); // Test should not reach this line
        } catch (DukeException e) {
            assertEquals("Wrong date format. Please Use format YYYY-MM-DD.", e.getMessage());
        }
    }

    @Test
    public void deadline_emptyDescription_exceptionThrown() {
        try {
            new Deadline("", "2023-01-01");
            fail(); // Test should not reach this line
        } catch (DukeException e) {
            assertEquals("The description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void deadline_correctFormat_success() {
        try {
            new Deadline("task", "2023-01-24");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void onDate_correctDate_success() {
        String testDate = "2023-01-24";
        LocalDate date = LocalDate.parse(testDate);
        try {
            assertEquals(true, new Deadline("task", testDate).onDate(date));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void onDate_incorrectDate_success() {
        String testDate = "2023-01-24";
        LocalDate date = LocalDate.parse(testDate);
        try {
            assertEquals(false, new Deadline("task", "2000-01-01").onDate(date));
        } catch (DukeException e) {
            fail();
        }
    }
}