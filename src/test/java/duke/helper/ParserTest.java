package duke.helper;
import duke.task.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void CheckValidCommand() throws DukeException {
        String command = "list";
        Parser parser =  new Parser();
        assertEquals(true, Parser.getCommand(command));
    }

    @Test
    public void CheckInvalidCommand() {
        Exception except = assertThrows(DukeException.class,() -> {
            Parser.getCommand("210ndeicn");
        } );

        String expectedmessage = "MEEEOOWWWWWW!!!! Invalid keyword! ";
        String actlmessage = except.toString(); //get the full message

        assertEquals(expectedmessage, actlmessage);
    }

}
