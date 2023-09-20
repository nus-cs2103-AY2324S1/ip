package ari;

import commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parser_ThrowsDukeException() {
        Parser p = new Parser();

        assertThrows(AriException.class, () -> p.parse("do my hw"));
    }

    @Test
    public void parser_TodoCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(ToDoCommand.class, p.parse("todo read book"));
    }

    @Test
    public void parser_EventCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(EventCommand.class, p.parse("event project meeting /from 20230709 1600 /to 1800"));
    }

    @Test
    public void parser_DeadlineCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(DeadlineCommand.class, p.parse("deadline hw /by 20230807 1400"));
    }

    @Test
    public void parser_ByeCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(ByeCommand.class, p.parse("bye"));
    }

    @Test
    public void parser_ListCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(ListCommand.class, p.parse("list"));
    }

    @Test
    public void parser_FindCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(FindCommand.class, p.parse("find book"));
    }


    @Test
    public void parser_MarkCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(MarkCommand.class, p.parse("mark 3"));
    }

    @Test
    public void parser_UnmarkCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(UnmarkCommand.class, p.parse("unmark 3"));
    }

    @Test
    public void parser_PostponeCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(PostponeCommand.class, p.parse("postpone 2 /by 20230608 1500"));
    }

    @Test
    public void parser_RescheduleCommandTest() throws AriException {
        Parser p = new Parser();

        assertInstanceOf(RescheduleCommand.class, p.parse("reschedule 6 /from 20230808 1600 /to 1700"));
    }
}