package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void greet() {
        assertEquals("Hello! I'm Max\nWhat can I do for you?", Ui.greet());
    }

    @Test
    public void exit() {
        assertEquals("Bye. Hope to see you again soon!", Ui.exit());
    }
}
