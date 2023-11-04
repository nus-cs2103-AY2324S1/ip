package seedu.dukduk;

import org.junit.jupiter.api.Test;
import dukduk.Ui;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testPrintHelpMessage() {
        Ui ui = new Ui();
        String helpMessage = ui.printHelpMessage();

        String expectedHelpMessage = "QUACKKK!!! Invalid command. Try the following commands instead:\n" +
                "~ todo <task>\n" +
                "~ deadline <task> /by yyyy-mm-dd hhmm\n" +
                "~ event <task> /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n" +
                "~ list\n" +
                "~ mark <task number>\n" +
                "~ unmark <task number>\n" +
                "~ delete <task number>\n" +
                "~ find <keyword>\n" +
                "~ bye\n";

        assertEquals(expectedHelpMessage, helpMessage);
    }

    @Test
    public void testPrintExit() {
        Ui ui = new Ui();
        String exitMessage = ui.printExit();
        assertEquals("Quackkk byeee!! Hope to see you again soon!", exitMessage);
    }
}

