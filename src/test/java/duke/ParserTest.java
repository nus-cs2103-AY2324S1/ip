package duke; //same package as the class being tested
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void parseCommandType_listCommandWithInvalidBack_exceptionThrown() {
        try {
            Parser.parseCommandType("list additionalInvalidWord");
        } catch (Exception e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void createNewTask_missingToFromEvent_worksNormally() {
        try {
            Task test = Parser.createNewTask("event 1 /from 2");
            assert test != null;
            assertEquals("[E][ ] 1 /from 2 (from:  to: )", test.toString());
        } catch (Exception e) {
            assertEquals("OOPS!!! The title, from and to sections cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createNewTask_invalidTask_returnsNull() {
        try {
            Task test = Parser.createNewTask("help");
            assertNull(test);
        } catch (Exception e) {
            assertEquals("Unexpected exception.", e.getMessage());
        }
    }
}

