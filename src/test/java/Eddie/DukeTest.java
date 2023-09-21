package Eddie;
import Eddie.Commands.AddCommand;
import Eddie.Tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DukeTest {
    @Test
    public void addTest() {
        Deadline d = new Deadline("Test", LocalDate.parse("2019-01-01"));
        AddCommand.execute(d);
        assertEquals(TaskList.get(0), d);
    }
}
