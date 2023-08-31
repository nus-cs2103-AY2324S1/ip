package brotherman.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final String line = "___________________________________________________________\n";

    @Test
    public void testShowWelcomeMessage(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        Ui ui = new Ui();
        ui.showWelcomeMessage();

        String expectedOutput = line
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "Do remember to add your date and time in DD/MM/YYYY\n";
        assertEquals(expectedOutput, outContent.toString());

//        System.setOut(System.out);

    }


}
