package urchatbot.commands;

import org.junit.jupiter.api.Test;
import urchatbot.exception.URChatBotException;
import urchatbot.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_ValidTodoCommand_ReturnsTodoCommand() throws URChatBotException {
        String command = "todo Buy groceries";
        Command parsedCommand = Parser.parse(command);
        assertTrue(parsedCommand instanceof TodoCommand);
        // Add more assertions here if needed
    }

    @Test
    public void parse_ValidDeadlineCommand_ReturnsDeadlineCommand() throws URChatBotException {
        String command = "deadline Submit report /by 2023-08-31 14:00";
        Command parsedCommand = Parser.parse(command);
        assertTrue(parsedCommand instanceof DeadlineCommand);
        // Add more assertions here if needed
    }

    @Test
    public void parse_InvalidCommand_ThrowsURChatBotException() {
        String command = "invalid command";
        assertThrows(URChatBotException.class, () -> Parser.parse(command));
    }

    // Add more test methods for other parsing scenarios

    // Test cases for error handling (e.g., invalid date format) if applicable

    // Test cases for edge cases and boundary conditions

}