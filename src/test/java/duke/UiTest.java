package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void printHelloTest() {
        Ui ui = new Ui();
        String br = ui.br;
        String expectedOutput = br
                + "My name is Jose Mourinho, I am the special one."
                + " I am a task planning bot that will record your tasks.\n"
                + "If you require help, type \"help\"\n"
                + br;
        assertEquals(expectedOutput, ui.printHello());
    }

}
