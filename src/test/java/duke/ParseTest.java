package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParseTest {
    @Test
    public void deadlineDetails_success() {
        Parser parser = new Parser();
        String deadline = "homework /by: 2023-12-01T12:20:00";
        String[] output = parser.deadlineDetails(deadline);
        assertEquals(output.length, 1);
    }
}
