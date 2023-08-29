package duke.parser;

import duke.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void invalidCommand_list() {
        Command c = Parser.parse("list xx");
        assertFalse(c instanceof ListCommand);
    }

    @Test
    public void validCommand_list() {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void invalidCommand_exit() {
        Command c = Parser.parse("bye ");
        assertFalse(c instanceof ExitCommand);
    }

    @Test
    void validCommand_exit() {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    void test_missingTask_todo() {
        Command c = Parser.parse("todo ");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_todo() {
        Command c = Parser.parse("todo xxx");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void test_missingDeadline_validTask_deadline() {
        Command c = Parser.parse("deadline xxx /by ");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_missingDeadline_missingTask_deadline() {
        Command c = Parser.parse("deadline ");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void test_validDeadline_missingTask_deadline() {
        Command c = Parser.parse("deadline  /by 21/06/02 1200");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void test_invalidDeadline_validTask_deadline() {
        Command c = Parser.parse("deadline xx /by lorem");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_validDeadline_deadline() {
        Command c = Parser.parse("deadline xx /by 21/08/02 1900");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void test_missingTask_missingFrom_missingTo_event() {
        Command c = Parser.parse("event");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_missingFrom_missingTo_event() {
        Command c = Parser.parse("event xx");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_validFrom_missingTo_event() {
        Command c = Parser.parse("event xx /from 21/05/02 1100");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_validFrom_validTo_event() {
        Command c = Parser.parse("event xx /from 21/05/02 1100 /to 21/05/02 1200");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void test_invalidTask_validFrom_validTo_event() {
        Command c = Parser.parse("event /from 21/05/02 1100 /to 21/05/02 1200");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_invalidFrom_invalidTo_event() {
        Command c = Parser.parse("event meet friends /from xx /to yy");
        assertFalse(c instanceof AddCommand);
    }

    @Test
    void test_validTask_invalidRange_event() {
        Command c = Parser.parse("event meet friends /from 21/05/02 1100 /to 21/05/02 1000");
        assertFalse(c instanceof AddCommand);
    }

}
