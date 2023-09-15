package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
import tasks.TaskList;

public class ByeCommandTest {

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
