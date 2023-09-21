package urchatbot.commands;

import org.junit.jupiter.api.Test;
import urchatbot.exception.URChatBotException;
import urchatbot.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import urchatbot.commands.*;
import urchatbot.exception.URChatBotException;

public class ParserTest {

    @Test
    public void testParseTodoCommand() throws URChatBotException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof TodoCommand);
        assertEquals("Buy groceries", ((TodoCommand) command).getTaskDescription());
    }

    @Test
    public void testParseDeadlineCommand() throws URChatBotException {
        Command command = Parser.parse("deadline Submit report /by 2023-12-31 23:59");
        assertTrue(command instanceof DeadlineCommand);
        DeadlineCommand deadlineCommand = (DeadlineCommand) command;
        assertEquals("Submit report", deadlineCommand.getTaskDescription());
        assertEquals("31 12 2023 23:59", deadlineCommand.getBy());
    }

    @Test
    public void testParseEventCommand() throws URChatBotException {
        Command command = Parser.parse("event Team meeting /from 2023-10-01 14:00 /to 2023-10-01 15:30");
        assertTrue(command instanceof EventCommand);
        EventCommand eventCommand = (EventCommand) command;
        assertEquals("Team meeting", eventCommand.getTaskDescription());
        assertEquals("01 10 2023 14:00", eventCommand.getFrom());
        assertEquals("01 10 2023 15:30", eventCommand.getTo());
    }


    @Test
    public void testParseInvalidCommand() {
        assertThrows(URChatBotException.class, () -> {
            Parser.parse("invalidCommand");
        });
    }
}
