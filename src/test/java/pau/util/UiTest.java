package pau.util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class UiTest {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void checkPrint() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    public void exit_byeInput_printsGoodbyeMessage() {
        Ui ui = new Ui();

        String expected = "byebyeee come play with me next time\n";
        ui.exit();
        assertEquals(expected, outContent.toString());
    }
}
