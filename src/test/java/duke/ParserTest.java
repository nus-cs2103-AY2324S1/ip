package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import command.AddTaskExecutable;
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
    public void simpleTest() {
        try {
            assertInstanceOf(ShutdownExecutable.class, testParser.parseToExecutable("bye"));
            assertInstanceOf(HelpExecutable.class, testParser.parseToExecutable("help"));
            assertInstanceOf(ListExecutable.class, testParser.parseToExecutable("list"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parseToExecutable("event name /from 2001-12-01 /to 2003-12-03"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parseToExecutable("deadline name /by 2002-02-01"));
            assertInstanceOf(AddTaskExecutable.class, testParser.parseToExecutable("todo name"));
            assertInstanceOf(MarkExecutable.class, testParser.parseToExecutable("mark 1"));
            assertInstanceOf(MarkExecutable.class, testParser.parseToExecutable("unmark 1"));
            assertInstanceOf(DeleteExecutable.class, testParser.parseToExecutable("delete 1"));
        } catch (InvalidVarException | InvalidCommandException e) {
            System.out.println(e.getMessage() + e.getCause());
            fail();
        }
    }

    @Test
    public void invalidCommandsTest() {
        assertThrows(InvalidCommandException.class, () -> testParser.parseToExecutable("fljdkfesjflks"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parseToExecutable("listlistlistlist"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parseToExecutable("     bye"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parseToExecutable("deadline"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parseToExecutable("deadlinne name /by 2001-01-01"));
        assertThrows(InvalidCommandException.class, () ->  testParser.parseToExecutable("deadLInE name /by 2001-01-01"));
    }

    @Test
    public void invalidVarsTest() {
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("deadline name /by 2001-1-1"));
        assertThrows(InvalidVarException.class, () -> testParser.parseToExecutable("bye bye"));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("mark x"));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("mark y"));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("deadline /by 2001-99-99"));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("delete two"));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("todo "));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("mark "));
        assertThrows(InvalidVarException.class, () ->  testParser.parseToExecutable("delete "));
    }
}
