package duke.command;

import duke.Duke;
import duke.TaskList;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DeadlineCommandTest {
    @Test
    public void execute_deadlineCommand_success() {
        Command command = new DeadlineCommand(new Deadline("return book", LocalDate.parse("2023-11-30")));
        command.setDuke(new Duke());
        String[] response = command.execute();

        assertArrayEquals(response, new String[]{"Got it. I've added this task:",
                "    [D][ ] return book (by: Nov 30 2023)",
                "Now you have 1 task in the list."});
    }
}
