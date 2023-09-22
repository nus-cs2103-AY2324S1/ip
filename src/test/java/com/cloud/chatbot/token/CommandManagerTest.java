package com.cloud.chatbot.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cloud.chatbot.exception.MissingInputException;



public class CommandManagerTest {
    @Test
    public void getCommand_whitespaceInput() throws MissingInputException {
        CommandManager commandManager = new CommandManager(" myCommand   arg1  arg2  ");
        assertEquals("mycommand", commandManager.getCommand());
    }

    @Test
    public void getDetails_whitespaceInput() throws MissingInputException {
        CommandManager commandManager = new CommandManager(" myCommand   arg1  arg2  ");
        assertEquals("arg1  arg2", commandManager.getDetails());
    }
}
