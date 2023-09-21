package duke.Command;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class HelpCommandTest {

    @Test
    public void executeTest() {
        String expected = "These are the commands that are permissible\n" +
                "todo description i.e todo math assignment, adds a todo task\n" +
                "deadline aa /by 630am 29june\n" +
                "deadline aa /by 06:30:00 2015-04-24\n" +
                "event aa /from 6am 26june /to 9am 29june\n" +
                "event aa /from 06:30:00 2015-04-24 /to 07:30:00 2015-04-24\n" +
                "delete idx i.e delete 1\n" +
                "mark idx i.e mark 1, marks a given task\n" +
                "unmark idx i.e unmark 1, unmarks a given task\n" +
                "list -list all things\n" +
                "find keyWord\n" +
                "archive idx\n" +
                "unarchive idk\n" +
                "archivelist\n" +
                "archivedelete idx\n" +
                "bye -exit system\n";

        Ui ui = new Ui("duke");
        String actual = ui.showHelp();
        assertEquals(actual, expected);
    }
}
