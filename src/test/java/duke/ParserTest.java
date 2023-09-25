package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
    }

    @Test
    public void parseToDoCommand_validInput_addsToDoTask() throws DukeException {
        String input = "todo Buy groceries";
        Parser.parseToDoCommand(input, taskList, ui);

        assertEquals("[T][ ] Buy groceries", taskList.getTask(0).toString());
    }

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

    @Test
    public void parseToDoCommand_descriptionWithLeadingSpaces_addsToDoTask() throws DukeException {
        String input = "todo    Exercise";
        Parser.parseToDoCommand(input, taskList, ui);

        assertEquals("[T][ ] Exercise", taskList.getTask(0).toString());
    }
}
