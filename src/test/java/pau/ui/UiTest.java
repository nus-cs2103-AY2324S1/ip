package pau.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Carries out unit testing for Ui class.
 */
public class UiTest {

    /**
     * Tests if chatbot prints introduction correctly.
     */
    @Test
    public void introduction_introduce_printsIntroduction() {
        Ui ui = new Ui();

        String expected = " HI! I'm \n"
                + "██████╗  █████╗ ██╗   ██╗\n"
                + "██╔══██╗██╔══██╗██║   ██║\n"
                + "██████╔╝███████║██║   ██║\n"
                + "██╔═══╝ ██╔══██║██║   ██║\n"
                + "██║     ██║  ██║╚██████╔╝\n"
                + "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n"
                + "                         \n"
                + "\n";
        ui.introduction();
        assertEquals(expected, ui.introduction());
    }

    /**
     * Tests if chatbot prints introduction correctly.
     */
    @Test
    public void exit_byeInput_printsGoodbyeMessage() {
        Ui ui = new Ui();

        String expected = "byebyeee come play with me next time\n";
        assertEquals(expected, ui.exit());
    }
}
