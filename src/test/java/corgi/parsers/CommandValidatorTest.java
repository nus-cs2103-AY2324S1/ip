package corgi.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class CommandValidatorTest {

    @Test
    public void validateArguments_invalidNumberOfArguments_throwsInvalidCommandFormatException() {
        CommandValidator validator = new CommandValidator();

        String command1 = "command /arg1 text1 /arg1 text2 /arg2 text3 /arg3 text4";
        String command2 = "command /arg1 text1 /arg2 text2 /arg2 text3 /arg3 text4";
        String command3 = "command /arg1 text1 /arg3 text2 /arg2 text3 /arg3 text4";

        Set<String> arguments = new HashSet<>();
        arguments.add("/arg1");
        arguments.add("/arg2");
        arguments.add("/arg3");

        InvalidCommandFormatException exception1 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command1, arguments));
        InvalidCommandFormatException exception2 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command2, arguments));
        InvalidCommandFormatException exception3 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command3, arguments));

        assertEquals("Invalid number of argument \"/arg1\" !", exception1.getMessage());
        assertEquals("Invalid number of argument \"/arg2\" !", exception2.getMessage());
        assertEquals("Invalid number of argument \"/arg3\" !", exception3.getMessage());
    }

    @Test
    public void validateArguments_missingArguments_throwsInvalidCommandFormatException() {
        CommandValidator validator = new CommandValidator();

        String command1 = "command /arg2 text1 /arg3 text2";
        String command2 = "command /arg1 text1 /arg3 text2";
        String command3 = "command /arg1 text1 /arg2 text2";

        Set<String> arguments = new HashSet<>();
        arguments.add("/arg1");
        arguments.add("/arg2");
        arguments.add("/arg3");

        InvalidCommandFormatException exception1 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command1, arguments));
        InvalidCommandFormatException exception2 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command2, arguments));
        InvalidCommandFormatException exception3 = assertThrows(InvalidCommandFormatException.class, () ->
                validator.validateArguments(command3, arguments));

        assertEquals("Missing argument \"/arg1\" !", exception1.getMessage());
        assertEquals("Missing argument \"/arg2\" !", exception2.getMessage());
        assertEquals("Missing argument \"/arg3\" !", exception3.getMessage());
    }
}
