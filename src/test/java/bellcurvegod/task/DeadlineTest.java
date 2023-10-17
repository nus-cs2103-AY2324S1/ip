package bellcurvegod.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void generateDeadlineFromInput_emptyDescription_exceptionThrown() {
        try {
            Deadline.generateDeadlineFromInput("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide any description to this Deadline.\n"
                + "To add a Deadline, enter \"deadline <description> /by <yyyy-mm-dd>\".\n", e.getMessage());
        }
    }

    @Test
    public void generateDeadlineFromInput_emptyDeadline_exceptionThrown() {
        try {
            Deadline.generateDeadlineFromInput("deadline return book");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide a deadline date to this Deadline.\n"
                + "To add a Deadline, enter \"deadline <description> /by <yyyy-mm-dd>\".\n", e.getMessage());
        }
    }
}
