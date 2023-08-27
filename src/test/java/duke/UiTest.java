package duke; //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void greetingTest() {
        Ui ui = new Ui();
        String lnspace = ui.lnspace;
        assertEquals(lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n", ui.greeting());
    }

    @Test
    public void endingTest() {
        Ui ui = new Ui();
        String lnspace = ui.lnspace;

        assertEquals("Bye. Hope to see you again soon!\n" + lnspace, ui.ending());
    }
}
