package oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddToDoCommand;
import commands.Command;
import commands.DeleteTaskCommand;
import commands.ExitCommand;
import commands.InvalidCommand;
import commands.ListTasksCommand;
import commands.MarkTaskCommand;
import commands.UnmarkTaskCommand;
import duke.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;


public class ParserTest {
    @Test // Test if a todo is added correctly.
    public void parseCommand_addToDoCommand_success() {
        // this casting is safe since we are expecting the command to give us a grocery shopping.
        AddToDoCommand command = (AddToDoCommand) Parser.parseCommand("todo grocery shopping");
        ToDo toDo = command.getTodo();
        assertEquals("grocery shopping", toDo.getName());
        assertEquals(false, toDo.isDone());
    }
    @Test // Test if a deadline is added correctly.
    public void parseCommand_addDeadlineCommand_success() {
        // this casting is safe since we are expecting the command to give us a AddDeadlineCommand.
        AddDeadlineCommand command = (AddDeadlineCommand)
                                        Parser.parseCommand("deadline return book"
                                                                + " /by 2024-01-01 0000");
        Deadline deadline = command.getDeadline();
        assertEquals("return book", deadline.getName());
        assertEquals(false, deadline.isDone());
        assertEquals("[D][] return book (by: 0000, 01 Jan, 2024)", deadline.toString());
    }
    @Test // Test if an event is added correctly.
    public void parseCommand_addEventCommand_success() {
        // this casting is safe since we are expecting the command to give us a AddEventCommand.
        AddEventCommand command = (AddEventCommand)
                                    Parser.parseCommand("event project meeting "
                                                            + "/from Mon 2pm /to 4pm");
        Event event = command.getEvent();
        assertEquals("project meeting", event.getName());
        assertEquals(false, event.isDone());
        assertEquals("[E][] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }
    @Test // Test if a mark command is generated correctly.
    public void parseCommand_markTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("mark 1");
        assertEquals(true, command instanceof MarkTaskCommand);
    }
    @Test // Test if an unmark command is generated correctly.
    public void parseCommand_unmarkTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("unmark 1");
        assertEquals(true, command instanceof UnmarkTaskCommand);
    }
    @Test // Test if a delete command is generated correctly.
    public void parseCommand_deleteTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("delete 1");
        assertEquals(true, command instanceof DeleteTaskCommand);
    }
    @Test // Test if an invalid command is generated correctly.
    public void parseCommand_invalidCommand_success() {
        Command command = Parser.parseCommand("asdf");
        assertEquals(true, command instanceof InvalidCommand);
    }
    @Test // Test if a list command is generated correctly.
    public void parseCommand_listTasksCommand_success() {
        Command command = Parser.parseCommand("list");
        assertEquals(true, command instanceof ListTasksCommand);
    }
    @Test // Test if an exit command is generated correctly.
    public void parseCommand_exitCommand_success() {
        Command command = Parser.parseCommand("bye");
        assertEquals(true, command instanceof ExitCommand);
    }
    @Test // Test if the correct exception is thrown for empty descriptions.
    public void parseCommand_emptyDescription_exceptionThrown() {
        try {
            Parser.parseCommand("todo");
        } catch (DukeException e) {
            assertEquals("Empty Description", e.getMessage());
        }
        try {
            Parser.parseCommand("deadline");
        } catch (DukeException e) {
            assertEquals("Empty Description", e.getMessage());
        }
        try {
            Parser.parseCommand("event");
        } catch (DukeException e) {
            assertEquals("Empty Description", e.getMessage());
        }
    }
}
