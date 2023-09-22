package duke.helper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.task.DukeException;

public class ParserTest {

    @Test
    public void checkValidCommand() throws DukeException {
        String command = "list";
        Parser parser = new Parser();
        assertEquals(true, Parser.getCommand(command));
    }

    @Test
    public void checkInvalidCommand() {
        Exception except = assertThrows(DukeException.class, () -> {
            Parser.getCommand("210ndeicn");
        });

        String expectedmessage = "MEEEOOWWWWWW!!!! Invalid keyword! ";
        String actlmessage = except.toString(); //get the full message

        assertEquals(expectedmessage, actlmessage);
    }

}
