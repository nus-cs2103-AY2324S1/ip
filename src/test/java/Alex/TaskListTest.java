package Alex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TaskListTest {

    @Test
    public void storeTest() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Try store something into TaskList and delete it
        TaskList.clearAll();
        String userInput = "todo read book";
        Command c = Parser.parse(userInput);
        c.execute();

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the captured output
        String expectedOutput = Ui.horizontalLine
                + "Got it. I've added this task:\n"
                + "  "
                + "[T][ ] read book" + "\n"
                + "Now you have " + 1 + " tasks in the list.\n"
                + Ui.horizontalLine
                + "\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void deleteTest() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Try store something into TaskList and delete it
        TaskList.clearAll();
        String userInput1 = "todo read book";
        Command c = Parser.parse(userInput1);
        c.execute();
        String userInput2 = "delete 2";
        Command c2 = Parser.parse(userInput2);
        c2.execute();
        String userInput3 = "delete 1";
        Command c3 = Parser.parse(userInput3);
        c3.execute();
        String userInput4 = "delete 1";
        Command c4 = Parser.parse(userInput4);
        c4.execute();

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the captured output
        String expectedOutput = Ui.horizontalLine
                + "Got it. I've added this task:\n"
                + "  "
                + "[T][ ] read book" + "\n"
                + "Now you have " + 1 + " tasks in the list.\n"
                + Ui.horizontalLine
                + "\n"
                + "_____________________________________________________________\n"
                + "OOPS!!! There is/are only 1 task(s) stored\n"
                + "_____________________________________________________________\n"
                + "\n"
                + "_____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + "  [T][ ] read book\n"
                + "Now you have 4 tasks in the list.\n"
                + "_____________________________________________________________\n"
                + "\n"
                + "_____________________________________________________________\n"
                + "OOPS!!! There is/are only 0 task(s) stored\n"
                + "_____________________________________________________________\n"
                + "\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);


    }
}
