package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import command.AddTaskExecutable;
import command.BindingExecutable;
import command.DeleteExecutable;
import command.HelpExecutable;
import command.ListExecutable;
import command.MarkExecutable;
import command.ShutdownExecutable;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;

public class ParserTest {
    Parser testParser = new Parser();
    @Test
    public void simpleCommandBookTest() {
        try {
            assertInstanceOf(ShutdownExecutable.class, testParser.parse("bye"));
            assertInstanceOf(HelpExecutable.class, testParser.parse("help"));
            assertInstanceOf(ListExecutable.class, testParser.parse("list"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parse("event name /from 2001-12-01 /to 2003-12-03"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parse("deadline name /by 2002-02-01"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parse("todo name"));
            assertInstanceOf(MarkExecutable.class, testParser.parse("mark 1"));
            assertInstanceOf(MarkExecutable.class, testParser.parse("unmark 1"));
            assertInstanceOf(DeleteExecutable.class, testParser.parse("delete 1"));
            assertInstanceOf(BindingExecutable.class, testParser.parse("rebind help /to h"));
            assertInstanceOf(BindingExecutable.class, testParser.parse("unbind h"));
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
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("deadlinne name /by 2001-01-01"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parse("deadLInE name /by 2001-01-01"));
    }

    @Test
    public void invalidVarsTest() {
        assertThrows(InvalidVarException.class, () ->  testParser.parse("deadline"));
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
