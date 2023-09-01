package Remy.command;

import Remy.ChatbotException;
import Remy.Storage;
import Remy.Task.TaskList;
import Remy.Ui;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {

    @Test
    public void MarkCommand_missingIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark"));
    }

    @Test
    public void MarkCommand_invalidIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark abc"));
    }

    @Test
    public void MarkCommand_negativeIndex_throwChatbotException() {
        assertThrows(ChatbotException.class, () -> new MarkCommand("mark -1"));
    }

    @Test
    public void MarkCommand_caseInsensitive_throwChatbotException() throws ChatbotException {
        MarkCommand mc = new MarkCommand("Mark 1");
        return;
    }

    @Test
    public void execute_invalidIndex_throwChatbotException() throws ChatbotException{
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(Paths.get(".", "data", "remy.ser"));
        MarkCommand mc = new MarkCommand("mark 2");
        assertThrows(ChatbotException.class, () -> mc.execute(taskList, ui, storage));
    }
}
