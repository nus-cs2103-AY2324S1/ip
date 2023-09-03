package duke.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;

public class UiTest {

    @Test
    @Tag("Basic test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Tag("Basic test")
    public void ui_initialisation_success() {
        Ui ui = new Ui();
        assertEquals(2, 2);
    }
}
