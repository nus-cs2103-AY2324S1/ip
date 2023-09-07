package LogicHandlers.CommandHandlers;

import Models.Deadline;
import Models.TaskArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineHandlerTest {
    private TaskArray taskArray;
    private DeadlineHandler deadlineHandler;

    @BeforeEach
    public void setUp() {
        taskArray = new TaskArray();
        deadlineHandler = new DeadlineHandler(taskArray);
    }

    @Test
    public void testValidDeadlineTask() {
        String input = "Finish homework /30-09-2023 1800";
        String expectedOutput = "Got it, I've added this task: \n" +
                "[D][ ] Finish homework (by: 30 Sep 2023 6:00 PM)\n" +
                "You now have 1 tasks in the list";

        String actualOutput = deadlineHandler.parseCommandContent(input);

        assertEquals(expectedOutput, actualOutput);
        assertEquals(1, taskArray.size());
        assertTrue(taskArray.get(0) instanceof Deadline);
    }

    @Test
    public void testEmptyCommandContent() {
        String input = "";
        String expectedOutput = "You cannot add an empty 'Deadline' task!";

        String actualOutput = deadlineHandler.parseCommandContent(input);

        assertEquals(expectedOutput, actualOutput);
        assertEquals(0, taskArray.size());
    }

    @Test
    public void testInvalidDateFormat() {
        String input = "Finish homework /2023-09-30";
        String expectedOutput = "Something went wrong! Please format the task properly and add it again. \n" +
                "Error: Exceptions.DukeInvalidDateTimeException: Invalid date and time format! Please try again.";

        String actualOutput = deadlineHandler.parseCommandContent(input);

        assertEquals(expectedOutput, actualOutput);
        assertEquals(0, taskArray.size());
    }

    @Test
    public void testInvalidDateTimeFormat() {
        String input = "Finish homework /invalid-date-time";
        String expectedOutput = "Something went wrong! Please format the task properly and add it again. \n" +
                "Error: Exceptions.DukeInvalidDateTimeException: Invalid date and time format! Please try again.";

        String actualOutput = deadlineHandler.parseCommandContent(input);

        assertEquals(expectedOutput, actualOutput);
        assertEquals(0, taskArray.size());
    }
}
