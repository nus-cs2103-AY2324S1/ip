package command;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ByeCommandTest {

    @Test
    public void testValidate() {
        // Arrange, Act, Assert
        assertTrue(ByeCommand.validate("bye"));

        assertFalse(ByeCommand.validate("bye some argument"));
        assertFalse(ByeCommand.validate("list"));
        assertFalse(ByeCommand.validate("todo some task"));
        assertFalse(ByeCommand.validate("deadline some task"));
    }

    @Test
    public void testExecute_ShowsByeMessage() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ByeCommand byeCommand = new ByeCommand("bye");

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        byeCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        String expectedOutput = "Bye. Hope to see you again soon!" + System.lineSeparator();

        // Assert
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testExecute_NoMessageDisplayedIfValidationFails() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ByeCommand invalidCommand = new ByeCommand("bye some argument");

        // Redirecting System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        invalidCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertEquals("", outContent.toString());
    }
}
