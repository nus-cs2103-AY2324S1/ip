package chatbot.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chatbot.commands.*;
import chatbot.exceptions.InvalidCommandException;

/**
 * Test class for Parser. Important as the parser is in charge of 
 * giving commands.
 * 
 * @author Owen Yeo
 */
public class ParserTest {
    
    /**
     * Tests if the parser can successfully take in valid commands.
     */
    @Test
    public void ParserInputSuccessTest() {
        String[] validCommands = { "bye", "list", "todo yes", "deadline yes", "event yes", "mark 1", "unmark 1", "delete 1" };

        for (String commandString : validCommands) {
            assertEquals(CommandType.parseInput(commandString), Parser.parse(commandString).getType());
        }
    }

    /**
     * Tests if the parser correctly throws an error when a wrong input is given.
     */
    @Test
    public void ParserInputErrorTest() {
        String[] invalidCommands = {"lol", "gg"};

        for (String commandString: invalidCommands) {
            assertThrows(InvalidCommandException.class, () -> Parser.parse(commandString));
        }
    }
}
