package buddy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import buddy.commands.AddDeadlineCommand;
import buddy.commands.AddEventCommand;
import buddy.commands.AddTodoCommand;
import buddy.commands.DeleteCommand;
import buddy.commands.ExitCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkAsDoneCommand;
import buddy.commands.MarkAsUndoneCommand;
import buddy.commands.UpdateDateCommand;
import buddy.commands.UpdateDescriptionCommand;
import buddy.exceptions.BuddyCommandException;
import buddy.utils.Parser;

public class ParserTest {

    @Test
    public void parser_success() throws BuddyCommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book", false);
        tasks.addTask(todo);
        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-06-06"), false);
        tasks.addTask(deadline);
        Event event = new Event("holiday trip", LocalDate.parse("2023-12-11"),
                LocalDate.parse("2023-12-14"), false);
        tasks.addTask(event);

        assertTrue(Parser.parse("bye", tasks) instanceof ExitCommand);
        assertTrue(Parser.parse("list", tasks) instanceof ListCommand);
        assertTrue(Parser.parse("mark 2", tasks) instanceof MarkAsDoneCommand);
        assertTrue(Parser.parse("unmark 2", tasks) instanceof MarkAsUndoneCommand);
        assertTrue(Parser.parse("delete 2", tasks) instanceof DeleteCommand);
        assertTrue(Parser.parse("todo read book", tasks) instanceof AddTodoCommand);
        assertTrue(Parser.parse("deadline return book /by 2023-06-06", tasks) instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event orientation week /from 2023-07-31 /to 2023-08-04", tasks)
                instanceof AddEventCommand);
        assertTrue(Parser.parse("update 1 /desc read finish book", tasks)
                instanceof UpdateDescriptionCommand);
        assertTrue(Parser.parse("update 2 /by 2023-10-10", tasks)
                instanceof UpdateDateCommand);
        assertTrue(Parser.parse("update 3 /from 2023-12-10", tasks)
                instanceof UpdateDateCommand);
        assertTrue(Parser.parse("update 3 /to 2023-12-15", tasks)
                instanceof UpdateDateCommand);
    }

    @Test
    public void parser_invalidDate_exceptionThrow() {
        BuddyCommandException thrown = assertThrows(BuddyCommandException.class, () -> {
            Parser.parse("deadline return book /by July 7th 2023", new TaskList());
        }, "Invalid date format, BuddyCommandException expected");

        assertEquals("Whoops! Try this instead: \n" + "Enter the date in YYYY-MM-DD format",
                thrown.getMessage());
    }

    @Test
    public void parser_invalidIndexType_exceptionThrow() {
        BuddyCommandException floatThrown = assertThrows(BuddyCommandException.class, () -> {
            Parser.parse("mark 1.1", new TaskList());
        }, "Non-integer index, BuddyCommandException expected");

        assertEquals("Whoops! Try this instead: \n" + "Enter the index as a positive integer",
                floatThrown.getMessage());

        BuddyCommandException wordThrown = assertThrows(BuddyCommandException.class, () -> {
            Parser.parse("unmark four", new TaskList());
        }, "Non-integer index, BuddyCommandException expected");

        assertEquals("Whoops! Try this instead: \n" + "Enter the index as a positive integer",
                wordThrown.getMessage());
    }

    @Test
    public void parser_indexOutOfBounds_exceptionThrow() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book", false));
        BuddyCommandException thrown = assertThrows(BuddyCommandException.class, () -> {
            Parser.parse("delete 5", tasks);
        }, "Zero index, BuddyCommandException expected");

        assertEquals("Whoops! Try this instead: \n" + "Index from 1 to 1",
                thrown.getMessage());
    }
}
