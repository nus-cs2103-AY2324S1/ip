package gudetama;

import gudetama.commands.HelpCommand;
import gudetama.storage.Storage;
import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
class HelpCommandTest {
    @Test
    void testExecute() {
        HelpCommand command = new HelpCommand();
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        command.execute(taskList, ui, storage);

        String actual = command.execute(taskList, ui, storage);
        String expected = "Here is a list of valid commands: \n" +
                "1. todo \n" +
                "2. event \n" +
                "3. deadline \n" +
                "4. mark \n" +
                "5. unmark \n" +
                "6. list \n" +
                "7. delete \n";

        System.setOut(System.out);

        assertEquals(expected, actual);
    }
}
