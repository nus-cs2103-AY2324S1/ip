package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the parseToDoCommand method of parser class.
 */
public class ParserTest {
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialise a dummy taskList and ui before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
    }

    /**
     * Tests the method with a valid input.
     *
     * @throws DukeException If the tested method throws DukeException.
     */
    @Test
    public void parseToDoCommand_validInput_addsToDoTask() throws DukeException {
        String input = "todo Buy groceries";
        Parser.parseToDoCommand(input, taskList, ui);

        assertEquals("[T][ ] Buy groceries", taskList.getTask(0).toString());
    }

    /**
     * Tests the method with an empty description.
     */
    @Test
    public void parseToDoCommand_emptyDescription_throwsDukeException() {
        String input = "todo ";
        try {
            Parser.parseToDoCommand(input, taskList, ui);
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    /**
     * Tests the method with description having leading spaces.
     *
     * @throws DukeException If the tested method throws DukeException.
     */
    @Test
    public void parseToDoCommand_descriptionWithLeadingSpaces_addsToDoTask() throws DukeException {
        String input = "todo    Exercise";
        Parser.parseToDoCommand(input, taskList, ui);

        assertEquals("[T][ ] Exercise", taskList.getTask(0).toString());
    }
}
