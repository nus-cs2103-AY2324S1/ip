package remy.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import remy.ChatbotException;



public class DeadlineCommandTest {
    @Test
    public void deadLineCommand_missingInfo_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline"));
    }

    @Test
    public void deadLineCommand_missingDelimiter_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc"));
    }

    @Test
    public void deadLineCommand_missingDateInfo_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by"));
    }

    @Test
    public void deadLineCommand_invalidDateMissingMonthAndDay_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by 2023"));
    }

    @Test
    public void deadLineCommand_invalidDateMissingDay_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new DeadlineCommand("deadline abc /by 2023-21"));
    }

    @Test
    public void deadLineCommand_correctInput_success() throws ChatbotException {
        DeadlineCommand dc = new DeadlineCommand("deadline abc /by 2023-10-23");
        return;
    }
}
