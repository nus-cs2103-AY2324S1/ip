package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void testLine() {
        Ui ui = new Ui();
        String expectedLine = "______________________________________________________________________________________\n";
        assertEquals(expectedLine, ui.line());
    }
}

