package bot.utils;

import bot.exceptions.InvalidTaskException;
import bot.utils.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void makeTask_validInput_success() throws InvalidTaskException {
        assertEquals("[T][ ] name1", Task.makeTask("todo name1").toString());

        assertEquals("[D][ ] name2 (by: Aug 10 2024)",
                Task.makeTask("deadline name2 /by 2024-08-10").toString());

        assertEquals("[E][ ] name3 (from: Jul 1 2024 to: Aug 1 2024)",
                Task.makeTask("event name3 /from 2024-07-01 /to 2024-08-01").toString());
    }

    @Test
    public void makeTask_missingDetailsToDo_throwsException() {
        try {
            Task.makeTask("todo");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Sorry, the todo description can't be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void makeTask_missingDetailsDeadline_throwsException() {
        try {
            Task.makeTask("deadline two");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Please make sure the deadline "
                            + "is written in the correct format:\n"
                            + "deadline ... /by ...",
                    e.getMessage());
        }
    }

    @Test
    public void makeTask_missingDetailsEvent_throwsException() {
        try {
            Task.makeTask("event /from 2020-10-10 /to 2020-12-10");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Sorry, the event description can't be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void makeTask_invalidDate_throwsException() {
        try {
            Task.makeTask("deadline kappa /by 2020-20-20");
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("One or more dates are invalid.",
                    e.getMessage());
        }
    }


}
