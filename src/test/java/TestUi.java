import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Task;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUi {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    public void testIntroEmptyList() {
        String line = "______________________________\n";
        String logo = " ____             _\n"
                + "|  _ \\           | |\n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\ |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/\n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        String greeting = (line
                + "Hi Barbie! Hi Ken!\n"
                + "\nI'm\n"
                + logo
                + "\n\nThis is the list of things you have today!");
        String question = ("\nWhat can I do for you?\n" + line + "\n[you]:");

        String expected = greeting + "\n" + question;

        Ui.intro(new ArrayList<>());
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testEmptyFileList() {
        Ui.listTasks(new ArrayList<>(), 0);
        String expected = "Hmm.. your list looks empty. To add items, use the 'todo', 'deadline' or 'party' commands!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

}
