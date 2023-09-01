import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import duke.Duke;
import duke.TaskList;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import java.io.*;

public class DukeTest {
    @Test
    public void testTodoCommand() {
        Duke duke = new Duke("./data/duke.txt");

        // Simulate user input to add a "todo" task
        String simulatedUserInput = "todo Read a book\nbye\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Redirect output to capture Duke's responses
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run Duke
        duke.run();

        // Capture Duke's output
        String dukeOutput = outputStream.toString();

        // Check if Duke's output contains the added "todo" task
        assertTrue(dukeOutput.contains("Got it. I've added this task:"));
        assertTrue(dukeOutput.contains("[T][ ] Read a book"));
    }

    @Test
    public void testDeadlineCommand() {
        Duke duke = new Duke("./data/duke.txt");

        // Simulate user input to add a "deadline" task
        String simulatedUserInput = "deadline Finish report /by 30/08/2023 1430\nbye\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Redirect output to capture Duke's responses
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run Duke
        duke.run();

        // Capture Duke's output
        String dukeOutput = outputStream.toString();

        // Check if Duke's output contains the added "deadline" task and displays it correctly
        assertTrue(dukeOutput.contains("Got it. I've added this task:"));
        assertTrue(dukeOutput.contains("[D][ ] Finish report (by 30/08/2023 1430)"));
    }

}

