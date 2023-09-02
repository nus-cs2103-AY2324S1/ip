package duke; //same package as the class being tested
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
            assert test != null;
            assertEquals("[E][ ] 1 (from: 2 to: )", test.toString());
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The title, from and to sections cannot be empty.", e.getMessage());
        }
    }
}

