package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void invalidDeleteCharacterInput() throws Exception {
        try {
            TaskList.deleteTask(-1231);
        } catch (Exception e) {
            assertEquals("Invalid Task entered. Please try again...", e.getMessage());
        }

    }

}
