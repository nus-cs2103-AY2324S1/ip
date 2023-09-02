package chatbot;

import chatbot.commands.ListCommand;
import chatbot.storage.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
class ListCommandTest {
    @Test
    void testExecute() {
        ListCommand command = new ListCommand();
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        command.execute(taskList, ui, storage);

        String actual = outputStream.toString().trim();
        String expected = "Here are the tasks in your list:";

        System.setOut(System.out);

        assertEquals(expected, actual);
    }
}


