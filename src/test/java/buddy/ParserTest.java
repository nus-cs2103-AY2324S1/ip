package buddy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import buddy.commands.AddDeadlineCommand;
import buddy.commands.AddEventCommand;
import buddy.commands.AddTodoCommand;
import buddy.commands.Command;
import buddy.commands.DeleteCommand;
import buddy.commands.ExitCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkAsDoneCommand;
import buddy.commands.MarkAsUndoneCommand;
import buddy.utils.BuddyException;
import buddy.utils.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parser_success() throws BuddyException {
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
    }

//    @Test
//    public void parser_outOfRange_exceptionThrown() {
//
//        BuddyException thrown = assertThrows(BuddyException.class, () -> {
//            Parser.parse("blah", new TaskList());
//        }, "BuddyException expected");
//
//        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", thrown.getMessage());
//    }

    @Test
    public void parser_invalidDate_exceptionThrow() {
        BuddyException thrown = assertThrows(BuddyException.class, () -> {
            Parser.parse("deadline return book /by July 7th 2023", new TaskList());
        }, "Invalid date format, BuddyException expected");

        assertEquals("OOPS! Please enter the date in YYYY-MM-DD format.", thrown.getMessage());
    }
}
