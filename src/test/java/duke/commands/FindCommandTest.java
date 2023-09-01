package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Todo;

public class FindCommandTest {
    private static final String invalidFormatMessage = String.join(
            "\n",
            "Invalid format for command `find`!",
            "Usage: find <KEYWORD>"
    );

    @Test
    public void run_findExistingTasks_success() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        todo.markAsDone();
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2023-09-10T12:00"));
        tasks.add(todo);
        tasks.add(deadline);
        Command command = new FindCommand("find book");

        CommandResult result = command.run(tasks);

        assertEquals(List.of(
                "Here are the matching tasks in your list:",
                "1. [T][X] read book",
                "2. [D][ ] return book (by 2023-09-10T12:00)"
        ), result.response);
    }

    @Test
    public void run_findTasksWithMultiwordKeyword_success() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        todo.markAsDone();
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2023-09-10T12:00"));
        tasks.add(todo);
        tasks.add(deadline);
        Command command = new FindCommand("find read book");

        CommandResult result = command.run(tasks);

        assertEquals(List.of("Here are the matching tasks in your list:", "1. [T][X] read book"), result.response);
    }

    @Test
    public void run_findNoTasks_commandExceptionThrown() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        todo.markAsDone();
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2023-09-10T12:00"));
        tasks.add(todo);
        tasks.add(deadline);
        Command command = new FindCommand("find car");

        CommandResult result = command.run(tasks);

        assertEquals(List.of("You have no matching tasks in your list."), result.response);
    }

    @Test
    public void run_missingArgKeyword_commandExceptionThrown() {
        try {
            new FindCommand("find");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }

    @Test
    public void run_missingKeywordWithSpace_commandExceptionThrown() {
        try {
            new FindCommand("find ");
        } catch (CommandException e) {
            assertEquals(invalidFormatMessage, e.getMessage());
        }
    }
}
