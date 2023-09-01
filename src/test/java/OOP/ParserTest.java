package OOP;  //same package as the class being tested

import Commands.AddDeadlineCommand;
import Commands.AddEventCommand;
import Commands.AddToDoCommand;
import Commands.Command;
import Commands.DeleteTaskCommand;
import Commands.ExitCommand;
import Commands.InvalidCommand;
import Commands.ListTasksCommand;
import Commands.MarkTaskCommand;
import Commands.UnmarkTaskCommand;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_addToDoCommand_success() {
        // this casting is safe since we are expecting the command to give us a grocery shopping ToDo
        AddToDoCommand command = (AddToDoCommand) Parser.parseCommand("todo grocery shopping");
        ToDo toDo = command.getTodo();
        assertEquals("grocery shopping",toDo.getName());
        assertEquals(false, toDo.isDone());
    }
    @Test
    public void parseCommand_addDeadlineCommand_success() {
        // this casting is safe since we are expecting the command to give us a AddDeadlineCommand
        AddDeadlineCommand command = (AddDeadlineCommand) Parser.parseCommand("deadline return book /by 2024-01-01 0000");
        Deadline deadline = command.getDeadline();
        assertEquals("return book", deadline.getName());
        assertEquals(false, deadline.isDone());
        assertEquals("[D][] return book (by: 0000, 01 Jan, 2024)", deadline.toString());
    }
    @Test
    public void parseCommand_addEventCommand_success() {
        // this casting is safe since we are expecting the command to give us a AddEventCommand
        AddEventCommand command = (AddEventCommand) Parser.parseCommand("event project meeting /from Mon 2pm /to 4pm");
        Event event = command.getEvent();
        assertEquals("project meeting", event.getName());
        assertEquals(false, event.isDone());
        assertEquals("[E][] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void parseCommand_markTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("mark 1");
        assertEquals(true, command instanceof MarkTaskCommand);
    }
    @Test
    public void parseCommand_unmarkTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("unmark 1");
        assertEquals(true, command instanceof UnmarkTaskCommand);
    }
    @Test
    public void parseCommand_deleteTaskCommand_success() {
        Parser.parseCommand("todo dummyTask");
        Command command = Parser.parseCommand("delete 1");
        assertEquals(true, command instanceof DeleteTaskCommand);
    }
    @Test
    public void parseCommand_invalidCommand_success() {
        Command command = Parser.parseCommand("asdf");
        assertEquals(true, command instanceof InvalidCommand);
    }
    @Test
    public void parseCommand_listTasksCommand_success() {
        Command command = Parser.parseCommand("list");
        assertEquals(true, command instanceof ListTasksCommand);
    }
    @Test
    public void parseCommand_exitCommand_success() {
        Command command = Parser.parseCommand("bye");
        assertEquals(true, command instanceof ExitCommand);
    }
    @Test
    public void parseCommand_emptyDescription_exceptionThrown() {
        try {
            assertEquals(new InvalidCommand(), Parser.parseCommand("todo"));
        } catch (Exception e) {
            assertEquals("\tEmpty Description", e.getMessage());
        }
        try {
            assertEquals( new InvalidCommand(), Parser.parseCommand("deadline"));
        } catch (Exception e) {
            assertEquals("\tEmpty Description", e.getMessage());
        }
        try {
            assertEquals(new InvalidCommand(), Parser.parseCommand("event"));
        } catch (Exception e) {
            assertEquals("\tEmpty Description", e.getMessage());
        }
    }
}
