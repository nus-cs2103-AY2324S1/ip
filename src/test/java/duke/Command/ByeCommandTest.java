package duke.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    @Test
    public  void executeTest() {
        Ui ui = new Ui("duke");
        String expected = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        String actual = ui.showGoodBye();
        assertEquals(actual, expected);
    }
}
