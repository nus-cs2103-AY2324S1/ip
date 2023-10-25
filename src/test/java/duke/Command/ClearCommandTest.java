package duke.Command;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearCommandTest {

    @Test
    public void executeTest() {
        String expected = "Now all tasks in your list have been cleared!";
        Ui ui = new Ui("duke");
        String actual = ui.showClear();
        assertEquals(actual, expected);
    }
}
