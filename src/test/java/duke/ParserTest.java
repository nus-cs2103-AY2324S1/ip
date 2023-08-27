package duke;  //same package as the class being tested
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parseCommandType_invalidCommand_exceptionThrown() {
        try {
            Parser.parseCommandType("commandThatMakesNoSense");
        } catch (Exception e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void newTask_missingToFromEvent_nullReturned() {
        try {
            Task test = Parser.newTask("event 1 /from 2");
            assertNull(test);
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The title, from and to sections cannot be empty.", e.getMessage());
        }
    }
}

