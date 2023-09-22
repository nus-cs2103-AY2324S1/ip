package Alex;

import Alex.commands.Command;
import Alex.commands.Parser;
import Alex.tasks.TaskList;
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
        String output = c.execute();
        System.out.println(output);

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the captured output
        String expectedOutput = "Got it. I've added this task:\n"
                + "  "
                + "[T][ ] read book" + "\n"
                + "Now you have " + 1 + " tasks in the list.\n";
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
        String output1 = c.execute();
        System.out.println(output1);
        String userInput2 = "delete 2";
        Command c2 = Parser.parse(userInput2);
        String output2 = c2.execute();
        System.out.println(output2);
        String userInput3 = "delete 1";
        Command c3 = Parser.parse(userInput3);
        String output3 = c3.execute();
        System.out.println(output3);
        String userInput4 = "delete 1";
        Command c4 = Parser.parse(userInput4);
        String output4 = c4.execute();
        System.out.println(output4);

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the captured output
        String expectedOutput = "Got it. I've added this task:\n"
            + "  [T][ ] read book\n"
            + "Now you have 1 tasks in the list.\n"
            + "OOPS!!! There is/are only 1 task(s) stored\n"
            + "Noted. I've removed this task:\n"
            + "  [T][ ] read book\n"
            + "Now you have 0 tasks in the list.\n\n"
            + "OOPS!!! There is/are only 0 task(s) stored\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);

    }
}
