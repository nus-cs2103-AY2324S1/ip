package com.alpha.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.alpha.exceptions.InvalidTaskException.DuplicateTaskException;
import com.alpha.tasks.TaskList;
import com.alpha.utils.DateTimeParser;

public class DeadlineCommandTest {

    @Test
    public void testExecute() {
        String taskName = "read book";
        String deadlineEnd = "2023-07-22 23:44";
        LocalDateTime parsedDeadlineEnd = DateTimeParser.parseDateTimeString(deadlineEnd);
        TaskList taskList = new TaskList();
        DeadlineCommand command = null;
        try {
            command = new DeadlineCommand(taskName, parsedDeadlineEnd, taskList);
        } catch (DuplicateTaskException e) {
            throw new RuntimeException(e);
        }
        String expectedResult =
                String.format("Got it. I've added this task:\n"
                                + "[D][ ] %s (by: %s)\n"
                                + "Now you have 1 tasks in the list.\n",
                        taskName,
                        DateTimeParser.parseDateTimeObjectToDisplay(parsedDeadlineEnd));

        String result = command.execute();

        assertEquals(expectedResult, result);
    }
}
