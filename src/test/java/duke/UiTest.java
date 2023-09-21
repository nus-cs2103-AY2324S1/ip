package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class UiTest {
    @Test
    public void printHelloTest() {
        Ui ui = new Ui();
        String expectedOutput = "My name is Jose Mourinho, I am the special one.\n"
                + "I am a task planning bot that will record your tasks.\n"
                + "If you require help, type \"help\"\n";
        assertEquals(expectedOutput, ui.printHello());
    }

    @Test
    public void invalidCommandTest() {
        Ui ui = new Ui();
        String expectedOutput = "I do not understand. Either I am wrong, or you are wrong,"
                + " so you are wrong\n";
        assertEquals(expectedOutput, ui.printError("I do not understand. Either I am wrong, or you are wrong,"
                + " so you are wrong"));
    }

}
