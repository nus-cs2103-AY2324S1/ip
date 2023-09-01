package ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    private static final ByteArrayOutputStream OUT_STREAM = new ByteArrayOutputStream();
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\033[0;31m";

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(
            new PrintStream(OUT_STREAM)
        );
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(System.out);
    }
    @Test
    public void testSingleLineDisplay() {
        Ui ui = new Ui();
        ui.displayMsg("Hello");
        String expected = String.format("\n    Hello\n\n");
        String actual = OUT_STREAM.toString().replace("\r\n", "\n");
        assertEquals(expected, actual);
        OUT_STREAM.reset();
    }

    @Test
    public void testMultiLineDisplay() {
        Ui ui = new Ui();
        ui.displayMsg(new String[] {
            "Hello, this line 1",
            "and... this is line 2",
            "and three!"
        });
        String expected = String.format(
            "\n    Hello, this line 1\n"
                + "    and... this is line 2\n"
                + "    and three!\n\n"
        );
        String actual = OUT_STREAM.toString().replace("\r\n", "\n");
        assertEquals(expected, actual);
        OUT_STREAM.reset();
    }

    @Test
    public void testSingleLineError() {
        Ui ui = new Ui();
        ui.displayError("Some error message.");
        String expected = String.format(
            "\n    " + RED + "Erm... error :(" + RESET
                + "\n    Some error message.\n\n"
        );
        String actual = OUT_STREAM.toString().replace("\r\n", "\n");
        assertEquals(expected, actual);
        OUT_STREAM.reset();
    }

    @Test
    public void testMultiLineError() {
        Ui ui = new Ui();
        ui.displayError(new String[] {
            "Error message",
            "Help tooltip",
            "Standard example"
        });
        String expected = String.format(
            "\n    " + RED + "Erm... error :(" + RESET
                + "\n    Error message"
                + "\n    Help tooltip"
                + "\n    Standard example\n\n"
        );
        String actual = OUT_STREAM.toString().replace("\r\n", "\n");
        assertEquals(expected, actual);
        OUT_STREAM.reset();
    }

    @Test
    public void testColors() {
        Ui ui = new Ui();
        ui.displayMsg(new String[] {
            Ui.cTxt("GREEN", Ui.Color.GREEN),
            Ui.cTxt("YELLOW", Ui.Color.YELLOW),
            Ui.cTxt("BLUE", Ui.Color.BLUE),
            Ui.cTxt("PURPLE", Ui.Color.PURPLE),
            Ui.cTxt("RED", Ui.Color.RED),
        });
        String expected = String.format(
            "\n    " + GREEN + "GREEN" + RESET
                + "\n    " + YELLOW + "YELLOW" + RESET
                + "\n    " + BLUE + "BLUE" + RESET
                + "\n    " + PURPLE + "PURPLE" + RESET
                + "\n    " + RED + "RED" + RESET + "\n\n"
        );
        String actual = OUT_STREAM.toString().replace("\r\n", "\n");
        assertEquals(expected, actual);
        OUT_STREAM.reset();
    }
}
