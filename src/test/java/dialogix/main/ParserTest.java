package dialogix.main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dialogix.command.AddDeadlineCommand;
import dialogix.command.AddEventCommand;
import dialogix.command.AddTodoCommand;
import dialogix.command.DeleteCommand;
import dialogix.command.DoneCommand;
import dialogix.command.ExitCommand;
import dialogix.command.FindCommand;
import dialogix.command.ListCommand;
import dialogix.command.UndoCommand;
import dialogix.exception.DialogixException;

class ParserTest {
    @Test
    void parserTest() throws DialogixException {
        // Testing exit command
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("exit") instanceof ExitCommand);
        assertTrue(Parser.parse("quit") instanceof ExitCommand);
        assertTrue(Parser.parse("end") instanceof ExitCommand);
        assertTrue(Parser.parse("goodbye") instanceof ExitCommand);

        // Testing list command
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("show tasks") instanceof ListCommand);
        assertTrue(Parser.parse("display list") instanceof ListCommand);
        assertTrue(Parser.parse("view all") instanceof ListCommand);
        assertTrue(Parser.parse("list tasks") instanceof ListCommand);

        // Testing find command
        assertTrue(Parser.parse("find abc") instanceof FindCommand);
        assertTrue(Parser.parse("search keyword") instanceof FindCommand);
        assertTrue(Parser.parse("lookup xyz") instanceof FindCommand);
        assertTrue(Parser.parse("filter tasks abc") instanceof FindCommand);
        assertTrue(Parser.parse("find in list xyz") instanceof FindCommand);

        // Testing done command
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);
        assertTrue(Parser.parse("mark 5 done") instanceof DoneCommand);
        assertTrue(Parser.parse("complete task 3") instanceof DoneCommand);
        assertTrue(Parser.parse("finish item 1") instanceof DoneCommand);
        assertTrue(Parser.parse("set as done 4") instanceof DoneCommand);

        // Testing delete command
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);
        assertTrue(Parser.parse("remove 5") instanceof DeleteCommand);
        assertTrue(Parser.parse("delete task 3") instanceof DeleteCommand);
        assertTrue(Parser.parse("erase item 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("discard 4") instanceof DeleteCommand);

        // Testing add todo command
        assertTrue(Parser.parse("todo todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("add task todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("create todo item todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("new todo: todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("insert todo - todo") instanceof AddTodoCommand);

        // Testing add deadline command with date
        assertTrue(Parser.parse("deadline deadline /by 25/07/2023 1500") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("add deadline by 2023-07-25 15:00") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("create deadline task by 25/07/23 3:00 PM") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("new deadline: deadline by 2023-07-25 3:00 PM") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("insert deadline - deadline /by 07/25/23 3:00 PM") instanceof AddDeadlineCommand);

        // Testing add deadline command with string
        assertTrue(Parser.parse("deadline deadline /by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("add deadline by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("create deadline task by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("new deadline: deadline by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("insert deadline - deadline /by deadlineBy") instanceof AddDeadlineCommand);

        // Testing add event command with date
        assertTrue(Parser.parse("event event /at 26/07/2023 1600") instanceof AddEventCommand);
        assertTrue(Parser.parse("add event at 2023-07-26 16:00") instanceof AddEventCommand);
        assertTrue(Parser.parse("create event on 26/07/23 at 4:00 PM") instanceof AddEventCommand);
        assertTrue(Parser.parse("new event: event at 2023-07-26 4:00 PM") instanceof AddEventCommand);
        assertTrue(Parser.parse("insert event - event /at 07/26/23 4:00 PM") instanceof AddEventCommand);

        // Testing add event command with string
        assertTrue(Parser.parse("event event /at eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("add event at eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("create event on eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("new event: event at eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("insert event - event /at eventAt") instanceof AddEventCommand);

        // Testing undo command
        assertTrue(Parser.parse("undo 2") instanceof UndoCommand);
        assertTrue(Parser.parse("reverse 5 steps") instanceof UndoCommand);
        assertTrue(Parser.parse("go back 3") instanceof UndoCommand);
        assertTrue(Parser.parse("revert 1") instanceof UndoCommand);
        assertTrue(Parser.parse("previous 4 actions") instanceof UndoCommand);
    }
}

