package command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.WoofInvalidCommandException;
import tasks.TaskList;

public class HelpCommandTest {
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
            ) -> HelpCommand.validate("help")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> HelpCommand.validate("")), (
            ) -> assertThrowsExactly(WoofInvalidCommandException.class, (
            ) -> HelpCommand.validate("bye some argument")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> HelpCommand.validate("list")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> HelpCommand.validate("todo some task")), (
            ) -> assertThrows(WoofInvalidCommandException.class, (
            ) -> HelpCommand.validate("deadline some task"))
        );
    }

    @Test
    public void testExecuteShowsHelpMessage() {
        // Arrange
        TaskList taskList = new TaskList(null);
        HelpCommand helpCommand = new HelpCommand("help");

        // Act
        String actualOutput = helpCommand.execute(taskList);
        String expectedOutput = "Woof! Woof! Here to help:" + System.lineSeparator()
            + System.lineSeparator()
            + "Available Commands:" + System.lineSeparator() + System.lineSeparator()
            + "1. Todo     : Add a simple to-do task."
            + System.lineSeparator()
            + "   Format   : `todo <description>`" + System.lineSeparator()
            + "   Example  : `todo study`" + System.lineSeparator()
            + System.lineSeparator()
            + "2. Deadline : Add a task with a deadline." + System.lineSeparator()
            + "   Format   : `deadline <description> /by <date>`" + System.lineSeparator()
            + "   Example  : `deadline assignment /by 2023-12-31`" + System.lineSeparator()
            + System.lineSeparator()
            + "3. Event    : Add an event with a start and end date." + System.lineSeparator()
            + "   Format   : `event <description> /from <date> /to <date>`" + System.lineSeparator()
            + "   Example  : `event enjoy /from 2023-11-25 /to 2024-01-14`" + System.lineSeparator()
            + System.lineSeparator()
            + "4. Mark     : Mark a task as completed." + System.lineSeparator()
            + "   Format   : `mark <taskIndex>`" + System.lineSeparator()
            + "   Example  : `mark 1`" + System.lineSeparator()
            + System.lineSeparator()
            + "5. Unmark   : Unmark a completed task as incomplete." + System.lineSeparator()
            + "   Format   : `unmark <taskIndex>`" + System.lineSeparator()
            + "   Example  : `unmark 1`" + System.lineSeparator()
            + System.lineSeparator()
            + "6. Delete   : Delete a task." + System.lineSeparator()
            + "   Format   : `delete <taskIndex>`" + System.lineSeparator()
            + "   Example  : `delete 2`" + System.lineSeparator()
            + System.lineSeparator()
            + "7. List     : View all your tasks." + System.lineSeparator()
            + "   Format   : `list`" + System.lineSeparator()
            + System.lineSeparator()
            + "8. Find     : Search for tasks containing n keywords." + System.lineSeparator()
            + "   Format   : `find <keyword1> ... <keyword n>`" + System.lineSeparator()
            + "   Example  : `find meeting project`" + System.lineSeparator()
            + System.lineSeparator()
            + "9. Sort     : Sort tasks by date and time." + System.lineSeparator()
            + "   Format   : `sort`" + System.lineSeparator()
            + System.lineSeparator()
            + "10. Bye     : Say goodbye to WoofWoof Bot." + System.lineSeparator()
            + "    Format : `bye`" + System.lineSeparator()
            + System.lineSeparator()
            + "11. Help    : Request help on how to use the bot." + System.lineSeparator()
            + "    Format : `help`" + System.lineSeparator()
            + System.lineSeparator()
            + "Notes:" + System.lineSeparator()
            + "- Replace `<description>` with a task description." + System.lineSeparator()
            + "- `<date>` should follow the yyyy-mm-dd date format." + System.lineSeparator()
            + "           e.g. 2023-12-31" + System.lineSeparator()
            + "- `<taskIndex>` should be the index of the task in the list" + System.lineSeparator()
            + "                you want to manage." + System.lineSeparator()
            + System.lineSeparator()
            + "Feel free to ask for help using the `help` command if you" + System.lineSeparator()
            + "have any questions or encounter issues." + System.lineSeparator()
            + "Woof Woof is here to make your life easier!";

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
