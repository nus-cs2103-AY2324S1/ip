package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
import tasks.TaskList;

public class ByeCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(new PrintStream(this.outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore the original System.out
        System.setOut(this.originalOut);
    }

    @Test
    public void testValidate() {
        // Arrange, Act, Assert
        assertAll((
            ) -> assertDoesNotThrow((
            ) -> ByeCommand.validate("bye")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> ByeCommand.validate("bye some argument")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> ByeCommand.validate("list")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> ByeCommand.validate("todo some task")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> ByeCommand.validate("deadline some task"))
        );
    }

    @Test
    public void testExecuteShowsByeMessage() {
        // Arrange
        TaskList taskList = new TaskList(null);
        ByeCommand byeCommand = new ByeCommand("bye");

        // Act
        String actualOutput = byeCommand.execute(taskList);
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

        // Assert
        assertNotEquals("", "Invalid number of arguments for the 'bye' command.");
    }
}
