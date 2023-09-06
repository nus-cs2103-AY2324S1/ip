package brandon.chatbot;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.UnknownCommand;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParseTest {
    @Test
    public void deadline_noDeadline_exceptionThrown() {
        final String input = "deadline asdf";
        parseAndAssertCommandType(input, UnknownCommand.class);
    }
    @Test
    public void todo_noTaskName_exceptionThrown() {
        final String input = "todo";
        parseAndAssertCommandType(input, UnknownCommand.class);
    }
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandType) {
        Command parsedCommand = new Parser().parseCommand(input);
        assertTrue(parsedCommand.getClass().isAssignableFrom(expectedCommandType));
        return (T) parsedCommand;
    }
}
