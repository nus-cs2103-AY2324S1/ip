package chatbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatCommandTest {

    @Test
    public void parse_plainInstruction_correctComponents() {
        ChatCommand command;

        command = ChatCommand.parse("");
        assertEquals("", command.getName());
        assertEquals("", command.getData());
        assertEquals(ChatCommand.Operation.Unknown, command.getOperation());
        assertFalse(command.hasParams());

        command = ChatCommand.parse("bye");
        assertEquals("bye", command.getName());
        assertEquals("", command.getData());
        assertEquals(ChatCommand.Operation.Exit, command.getOperation());
        assertFalse(command.hasParams());

        command = ChatCommand.parse("test abc 123--456");
        assertEquals("test", command.getName());
        assertEquals("abc 123--456", command.getData());
        assertEquals(ChatCommand.Operation.Unknown, command.getOperation());
        assertFalse(command.hasParams());
        assertFalse(command.hasParam("456"));
        assertFalse(command.hasParamWithUsefulValue("456"));
        assertNull(command.getParam("456"));

        command = ChatCommand.parse(" todo blabla\tblah blah\n123\n");
        assertEquals("todo", command.getName());
        assertEquals("blabla blah blah 123", command.getData());
        assertEquals(ChatCommand.Operation.AddTodo, command.getOperation());
        assertFalse(command.hasParams());
        assertFalse(command.hasParam("blah"));
        assertFalse(command.hasParamWithUsefulValue("blah"));
        assertNull(command.getParam("blah"));
    }

    @Test
    public void parse_instructionWithParameters_correctComponents() {
        ChatCommand command;

        command = ChatCommand.parse("--magic parameter");
        assertEquals("", command.getName());
        assertEquals("", command.getData());
        assertEquals(ChatCommand.Operation.Unknown, command.getOperation());
        assertTrue(command.hasParams());
        assertTrue(command.hasParam("magic"));
        assertTrue(command.hasParamWithUsefulValue("magic"));
        assertEquals("parameter", command.getParam("magic"));

        command = ChatCommand.parse("todo --missing");
        assertEquals("todo", command.getName());
        assertEquals("", command.getData());
        assertEquals(ChatCommand.Operation.AddTodo, command.getOperation());
        assertTrue(command.hasParams());
        assertTrue(command.hasParam("missing"));
        assertFalse(command.hasParamWithUsefulValue("missing"));
        assertEquals("", command.getParam("missing"));

        command = ChatCommand.parse("deadline message 123--456 --by date part");
        assertEquals("deadline", command.getName());
        assertEquals("message 123--456", command.getData());
        assertEquals(ChatCommand.Operation.AddDeadline, command.getOperation());
        assertTrue(command.hasParams());
        assertTrue(command.hasParam("by"));
        assertFalse(command.hasParam("456"));
        assertTrue(command.hasParamWithUsefulValue("by"));
        assertEquals("date part", command.getParam("by"));

        command = ChatCommand.parse("list something  nice\t--anything\n--at\tall\n");
        assertEquals("list", command.getName());
        assertEquals("something  nice", command.getData());
        assertEquals(ChatCommand.Operation.List, command.getOperation());
        assertTrue(command.hasParams());
        assertEquals("", command.getParam("anything"));
        assertFalse(command.hasParamWithUsefulValue("anything"));
        assertEquals("all", command.getParam("at"));
        assertTrue(command.hasParamWithUsefulValue("at"));
        assertNull(command.getParam("all"));
        assertFalse(command.hasParamWithUsefulValue("all"));
    }

}
