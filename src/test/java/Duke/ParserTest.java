package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import command.UserInterface;

/**
 * The type Parser test.
 */
public class ParserTest {

    /**
     * To test whether it throws exception for invalid task number.
     */
    @Test
    public void markInvalidTaskNumber() {
        try {
            UserInterface userInterface = new UserInterface();
            File fileName = new File("data/duke.txt");
            Storage storage = new Storage(fileName);
            TaskManager taskManager = new TaskManager(storage.load());
            Parser parser = new Parser(taskManager, userInterface, storage);
            parser.parseCommand("mark 200");
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number.", e.getMessage());
        }
    }

    /**
     * To test whether it throws the correct exception for empty description.
     */
    @Test
    public void emptyDescription() {
        try {
            UserInterface userInterface = new UserInterface();
            File fileName = new File("data/duke.txt");
            Storage storage = new Storage(fileName);
            TaskManager taskManager = new TaskManager(storage.load());
            Parser parser = new Parser(taskManager, userInterface, storage);
            parser.parseCommand("todo");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    /**
     * To test whether correct exception thrown for wrong number formats.
     */
    @Test
    public void wrongNumberFormat() {
        try {
            UserInterface userInterface = new UserInterface();
            File fileName = new File("data/duke.txt");
            Storage storage = new Storage(fileName);
            TaskManager taskManager = new TaskManager(storage.load());
            Parser parser = new Parser(taskManager, userInterface, storage);
            parser.parseCommand("delete four");
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please provide a valid task number.", e.getMessage());
        }
    }

    /**
     * To test that there is no exception thrown for valid inputs.
     */
    @Test
    public void validInputs() {
        assertDoesNotThrow(() -> {
            UserInterface userInterface = new UserInterface();
            File fileName = new File("data/duke.txt");
            Storage storage = new Storage(fileName);
            TaskManager taskManager = new TaskManager(storage.load());
            Parser parser = new Parser(taskManager, userInterface, storage);
            parser.parseCommand("mark 1");
        });
    }
}
