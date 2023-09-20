package duke;
import commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parser_ThrowsDukeException() {
        Parser p = new Parser();

        assertThrows(DukeException.class, () -> p.parse("do my hw"));
    }

    @Test
    public void parser_TodoCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(ToDoCommand.class, p.parse("todo read book"));
    }

    @Test
    public void parser_EventCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(EventCommand.class, p.parse("event project meeting /from 20230709 1600 /to 1800"));
    }

    @Test
    public void parser_DeadlineCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(DeadlineCommand.class, p.parse("deadline hw /by 20230807 1400"));
    }

    @Test
    public void parser_ByeCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(ByeCommand.class, p.parse("bye"));
    }

    @Test
    public void parser_ListCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(ListCommand.class, p.parse("list"));
    }

    @Test
    public void parser_FindCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(FindCommand.class, p.parse("find book"));
    }


    @Test
    public void parser_MarkCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(MarkCommand.class, p.parse("mark 3"));
    }

    @Test
    public void parser_UnmarkCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(UnmarkCommand.class, p.parse("unmark 3"));
    }

    @Test
    public void parser_PostponeCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(PostponeCommand.class, p.parse("postpone 2 /by 20230608 1500"));
    }

    @Test
    public void parser_RescheduleCommandTest() throws DukeException {
        Parser p = new Parser();

        assertInstanceOf(RescheduleCommand.class, p.parse("reschedule 6 /from 20230808 1600 /to 1700"));
    }
}