package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
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
