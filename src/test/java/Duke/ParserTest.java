package Duke;

import org.junit.jupiter.api.Test;
import Duke.*;
import Duke.Tasks.*;
import Duke.Exceptions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    public void testIncorrectInput() {
        Ui ui = new Ui();

        TaskList tasks = new TaskList("empty");

        boolean correctness = false;

        try {
            tasks.input("nonsense");
        } catch (IncompleteInput e) {
            correctness = true;
        } catch (InvalidInput e) {

        } finally {
            assertEquals(true, correctness);
        }
    }
}
