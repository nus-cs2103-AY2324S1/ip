package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import command.AddTaskCommand;
import command.DeleteCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ShutdownCommand;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;

public class ParserTest {
    Parser testParser = new Parser();
    @Test
    public void simpleTest() {
        try {
            assertInstanceOf(ShutdownCommand.class, testParser.parse("bye"));
            assertInstanceOf(HelpCommand.class, testParser.parse("help"));
            assertInstanceOf(ListCommand.class, testParser.parse("list"));
            assertInstanceOf(AddTaskCommand.class, testParser.parse("event name /from 2001-12-01 /to 2003-12-03"));
            assertInstanceOf(AddTaskCommand.class, testParser.parse("deadline name /by 2002-02-01"));
            assertInstanceOf(AddTaskCommand.class, testParser.parse("todo name"));
            assertInstanceOf(MarkCommand.class, testParser.parse("mark 1"));
            assertInstanceOf(MarkCommand.class, testParser.parse("unmark 1"));
            assertInstanceOf(DeleteCommand.class, testParser.parse("delete 1"));
        } catch (InvalidVarException | InvalidCommandException e) {
            System.out.println(e.getMessage() + e.getCause());
            fail();
        }
    }

    @Test
    public void invalidCommandsTest() {
        assertThrows(InvalidCommandException.class, () -> testParser.parse("fljdkfesjflks"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("listlistlistlist"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("     bye"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("deadline"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("deadlinne name /by 2001-01-01"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("deadLInE name /by 2001-01-01"));
    }

    @Test
    public void invalidVarsTest() {
        assertThrows(InvalidVarException.class, () ->  testParser.parse("deadline name /by 2001-1-1"));
        assertThrows(InvalidVarException.class, () -> testParser.parse("bye bye"));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("mark x"));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("mark y"));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("deadline /by 2001-99-99"));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("delete two"));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("todo "));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("mark "));
        assertThrows(InvalidVarException.class, () ->  testParser.parse("delete "));
    }
}
