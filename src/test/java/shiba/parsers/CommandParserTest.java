package shiba.parsers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import shiba.tasks.PersistentTaskList;
import shiba.tasks.TaskListStub;

public class CommandParserTest {
    private final PersistentTaskList tasks = new TaskListStub();

    @Test
    public void processUserInput_bye() {
        assertTrue(new CommandParser(tasks).processUserInput("bye"));
    }

    @Test
    public void processUserInput_notBye() {
        assertFalse(new CommandParser(tasks).processUserInput("list"));
        assertFalse(new CommandParser(tasks).processUserInput("mark"));
        assertFalse(new CommandParser(tasks).processUserInput("unmark"));
        assertFalse(new CommandParser(tasks).processUserInput("todo"));
        assertFalse(new CommandParser(tasks).processUserInput("deadline"));
        assertFalse(new CommandParser(tasks).processUserInput("event"));
        assertFalse(new CommandParser(tasks).processUserInput("delete"));
        assertFalse(new CommandParser(tasks).processUserInput("find"));
        assertFalse(new CommandParser(tasks).processUserInput("help"));
        assertFalse(new CommandParser(tasks).processUserInput("pat"));
        assertFalse(new CommandParser(tasks).processUserInput("boop"));
        assertFalse(new CommandParser(tasks).processUserInput("bellyrub"));
    }
}
