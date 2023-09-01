package pau.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Carries out unit testing for Ui class.
 */
public class UiTest {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Parses the printed output into a string.
     */
    @BeforeEach
    public void checkPrint() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tests if chatbot prints introduction correctly.
     */
    @Test
    public void introduction_introduce_printsIntroduction() {
        Ui ui = new Ui();

        String expected = " HI! I'm \n" +
                "██████╗  █████╗ ██╗   ██╗\n" +
                "██╔══██╗██╔══██╗██║   ██║\n" +
                "██████╔╝███████║██║   ██║\n" +
                "██╔═══╝ ██╔══██║██║   ██║\n" +
                "██║     ██║  ██║╚██████╔╝\n" +
                "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n" +
                "                         \n" +
                "\n" +
                "ENTERTAIN MEEE\n";
        ui.introduction();
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests if chatbot prints introduction correctly.
     */
    @Test
    public void exit_byeInput_printsGoodbyeMessage() {
        Ui ui = new Ui();

        String expected = "byebyeee come play with me next time\n";
        ui.exit();
        assertEquals(expected, outContent.toString());
    }
}
