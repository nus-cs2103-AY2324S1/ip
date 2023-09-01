package Remy.command;

import Remy.ChatbotException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineCommandTest {
    @Test
    public void DeadLineCommand_missingInfo_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline"));
    }

    @Test
    public void DeadLineCommand_missingInfo_throwChatbotException_1() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc"));
    }

    @Test
    public void DeadLineCommand_missingInfo_throwChatbotException_2() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by"));
    }

    @Test
    public void DeadLineCommand_invalidDate_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by 2023"));
    }

    @Test
    public void DeadLineCommand_invalidDate_throwChatbotException_2() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by 2023-21"));
    }

    @Test
    public void DeadLineCommand_correctInput_success() throws ChatbotException {
        DeadlineCommand dc = new DeadlineCommand("deadline abc /by 2023-10-23");
        return;
    }
}
