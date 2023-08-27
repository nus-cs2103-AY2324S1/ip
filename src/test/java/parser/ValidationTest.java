package parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import commands.Command;
import errors.DotException;
import errors.TaskError;
import tasks.TaskList;

public class ValidationTest {
    @Test
    public void intIfValidCommandSpaceNumber_invalidCommand_exceptionThrown() {
        try {
            int number = Validation.intIfValidCommandSpaceNumber("mark a",
                TaskError.ERR_USING_MARK);
        } catch (DotException e) {
            Assertions.assertEquals("Invalid number given",
                e.getMessage());
        }
    }

}
