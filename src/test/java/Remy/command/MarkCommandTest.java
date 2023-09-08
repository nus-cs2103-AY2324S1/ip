package remy.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

public class MarkCommandTest {

    @Test
    public void markCommand_missingIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark"));
    }

    @Test
    public void markCommand_invalidIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark abc"));
    }

    @Test
    public void markCommand_negativeIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark -1"));
    }

    @Test
    public void markCommand_caseInsensitive_success() throws ChatbotException {
        MarkCommand mc = new MarkCommand("Mark 1");
        return;
    }

    @Test
    public void execute_invalidIndex_throwChatbotException() throws ChatbotException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(Paths.get(".", "data", "remy.ser"));
        MarkCommand mc = new MarkCommand("mark 2");
        assertThrows(ChatbotException.class, () -> mc.execute(taskList, ui, storage));
    }
}
