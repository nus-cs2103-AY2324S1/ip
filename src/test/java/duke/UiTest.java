package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class UiTest {
    private Ui ui = new Ui();
    @Test
    public void testOpening() {
        assertEquals(ui.showWelcome(), "Hello, I'm Prawn\nWhat would you like me to do sire? ");
    }
    @Test
    public void testIoError() {
        assertEquals(ui.showIoError(), "Error in creating file");
    }
    @Test
    public void testUnknownCommand() {
        assertEquals(ui.showUnknownCommand(), "I do not understand this command");
    }
}
