package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import duke.exceptions.IncompleteDescriptionException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void dataToTask_completeDescription_success() {
        String todo = "T | 0 | read book";
        String deadline = "D | 0 | return book | June 6th";
        String event = "E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm";
        try {
            ToDo todoTask = new ToDo("read book");
            Deadline deadlineTask = new Deadline("return book", "June 6th");
            Event eventTask = new Event("project meeting", "Aug 6th 2pm", "Aug 6th 4pm");
            assertEquals(todoTask.toString(), Parser.dataToTask(todo).toString());
            assertEquals(deadlineTask.toString(), Parser.dataToTask(deadline).toString());
            assertEquals(eventTask.toString(), Parser.dataToTask(event).toString());
        } catch (IncompleteDescriptionException ex) {
            fail();
        }
    }

    @Test
    public void dataToTask_incompleteDescription_fail() {
        String errorMsg = Ui.divider + "\n"
                + "☹ OOPS!!! The description of the task is incomplete or incorrect."
                + "\n" + Ui.divider + "\n";
        try {
            String todo = "T | 0 |";
            Parser.dataToTask(todo);
            fail();
        } catch (IncompleteDescriptionException ex) {
            assertEquals(errorMsg, ex.toString());
        }
        try {
            String deadline = "D | 0 | | June 6th";
            Parser.dataToTask(deadline).toString();
            fail();
        } catch (IncompleteDescriptionException ex) {
            assertEquals(errorMsg, ex.toString());
        }
        try {
            String event = "| 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm";
            Parser.dataToTask(event);
            fail();
        } catch (IncompleteDescriptionException ex) {
            assertEquals(errorMsg, ex.toString());
        }
    }
}