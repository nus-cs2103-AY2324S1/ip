package gudetama;

import gudetama.commands.ListCommand;
import gudetama.storage.Storage;
import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
class ListCommandTest {
    @Test
    void testExecute() {
        ListCommand command = new ListCommand();
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String actual = command.execute(taskList, ui, storage);
        String expected = "Here are the tasks in your list:\n";

        System.setOut(System.out);

        assertEquals(expected, actual);
    }
}


