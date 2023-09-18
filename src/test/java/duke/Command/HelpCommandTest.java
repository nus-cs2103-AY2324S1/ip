package duke.Command;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class HelpCommandTest {

    @Test
    public void executeTest() {
        String expected = "These are the commands that are permissible\n" +
                "list -list all things\n" +
                "bye -exit system\n" +
                "mark/unmark idx i.e mark 1, marks a given task\n" +
                "delete idx i.e delete 1\n" +
                "todo description i.e todo math assignment, adds a todo task\n" +
                "deadline aa /by 630am 29june\n" +
                "deadline aa /by 06:30:00 2015-04-24\n" +
                "event aa /from 6am 26june /to 9am 29june\n" +
                "archive idx\n" +
                "unarhive idk\n" +
                "archivelist\n" +
                "archivedelete idx\n";
        Ui ui = new Ui("duke");
        String actual = ui.showHelp();
        assertEquals(actual, expected);
    }
}
