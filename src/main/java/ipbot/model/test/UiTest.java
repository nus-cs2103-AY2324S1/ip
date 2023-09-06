package ipbot.model.test;

import ipbot.model.TaskStub;
import ipbot.util.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Adapted from: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
 */
public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(this.outContent));
        System.setErr(new PrintStream(this.errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(this.originalOut);
        System.setErr(this.originalErr);
    }

    @Test
    public void testPrintTaskListFormat() {
        Ui ui = new Ui();
        ui.printTaskListFormat(new TaskStub("asdf"), 1);
        ui.printTaskListFormat(new TaskStub("asdf"), 2);
        ui.printTaskListFormat(new TaskStub("asdf"), 3);
        Assertions.assertEquals(
                "1. <Some Task String>\n" +
                "2. <Some Task String>\n" +
                "3. <Some Task String>\n",
                this.outContent.toString());
    }

    @Test
    public void testPrintAddedItem() {
        Ui ui = new Ui();
        ui.printAddedItem(new TaskStub("asdf"), "taskType");
        Assertions.assertEquals(
                "Added taskType item: <Some Task String>\r\n",
                this.outContent.toString());
    }
}
