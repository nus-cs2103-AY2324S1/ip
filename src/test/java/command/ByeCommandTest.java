package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import tasks.TaskList;
public class ByeCommandTest {

    @Test
    public void testValidate() {
        // Arrange, Act, Assert
        assertEquals("", ByeCommand.validate("bye"));

        assertNotEquals("", ByeCommand.validate("bye some argument"));
        assertNotEquals("", ByeCommand.validate("list"));
        assertNotEquals("", ByeCommand.validate("todo some task"));
        assertNotEquals("", ByeCommand.validate("deadline some task"));
    }

    @Test
    public void testExecuteShowsByeMessage() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ByeCommand byeCommand = new ByeCommand("bye");

        // Act
        String actualOutput = byeCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        String expectedOutput = "Bye. Hope to see you again soon!" + System.lineSeparator()
                + "Closing Woof Woof..." + System.lineSeparator();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecuteNoMessageDisplayedIfValidationFails() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ByeCommand invalidCommand = new ByeCommand("bye some argument");

        // Act
        invalidCommand.execute(taskList);

        // Restore System.out
        System.setOut(System.out);

        // Assert
        assertNotEquals("", "Invalid number of arguments for the 'bye' command.");
    }
}
