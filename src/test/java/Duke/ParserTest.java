package Duke;

import org.junit.jupiter.api.Test;
import Duke.*;
import Duke.Tasks.*;
import Duke.Exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testInvalidInput() {
        Ui ui = new Ui();

        boolean passed = false;

        TaskList tasks = new TaskList("empty");

        try {
            tasks.input("nonsense");
        } catch (InvalidInput e) {
            passed = true;
        } catch (IncompleteInput e) {

        } finally {
            assertEquals(true, passed);
        }
    }
}
