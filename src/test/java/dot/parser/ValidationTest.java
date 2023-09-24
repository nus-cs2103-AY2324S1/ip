package dot.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dot.errors.DotException;
import dot.errors.TaskError;

public class ValidationTest {
    @Test
    public void intIfValidCommandSpaceNumber_invalidCommand_exceptionThrown() {
        try {
            Validation.getIntIfValidCommandSpaceNumber("mark a",
                    TaskError.ERR_USING_MARK);
        } catch (DotException e) {
            Assertions.assertEquals("Invalid number given",
                    e.getMessage());
        }
    }

}
