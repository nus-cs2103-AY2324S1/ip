package jeo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    public void output_helloMessage_correctOutput() {
        String output = ui.hello();
        String expectedOutput = "Hello, I'm Je-O" + "\n" + "What can I do for you?";
        assertEquals(output, expectedOutput);
    }

    @Test
    public void output_byeMessage_correctOutput() {
        String output = ui.bye();
        String expectedOutput = "Bye. Hope to see you again soon!";
        assertEquals(output, expectedOutput);
    }
}
