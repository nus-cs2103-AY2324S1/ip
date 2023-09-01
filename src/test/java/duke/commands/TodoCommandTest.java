package duke.commands;

import duke.TaskList;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `todo`!",
            "Usage: todo <DESCRIPTION>"
    );

    @Test
    public void run_validTodo_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new TodoCommand("todo read book");

        CommandResult result = command.run(tasks);

        assertEquals(tasks.size(), 1);
        assertEquals(
                List.of("Got it. I've added this task:", "[T][ ] read book", "Now you have 1 task in the list."),
                result.response
        );

        Task todo = tasks.get(0);
        assertFalse(todo.isDone());
    }

    @Test
    public void run_missingArgument_commandExceptionThrown() {
        try {
            new TodoCommand("todo");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingArgumentWithSpace_commandExceptionThrown() {
        try {
            new TodoCommand("todo ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }
}
