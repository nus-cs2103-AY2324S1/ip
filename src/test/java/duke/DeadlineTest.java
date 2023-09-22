package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void deadlineToStringTest() {
        Deadline d = new Deadline("a deadline here", "2022-02-10");
        String result = d.toString();
        String expected = "[D][ ] a deadline here (by: Feb 10 2022)";
        assertEquals(expected, result);
    }

    @Test
    public void deadlineStringInFileTest() {
        Deadline d = new Deadline("a deadline here ", "2022-02-10");
        String result = d.stringInFile();
        String expected = "D | 0 | a deadline here | 2022-02-10";
        assertEquals(expected, result);
    }
}
