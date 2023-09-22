package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void checkValidTodoTask() {
        Exception except = assertThrows(DukeException.class, () -> {
            new Todo("");
        });

        String expectedmessage = "MEEEOOWWWWWW!!!! No Description given!";
        String actlmessage = except.toString(); //get the full message

        assertEquals(expectedmessage, actlmessage);
    }
}
