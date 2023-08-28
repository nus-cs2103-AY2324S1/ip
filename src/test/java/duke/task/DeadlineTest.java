package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void DeadlineTest() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-06-06"));
        assertEquals("[D] [ ] return book (by: Jun 06 2023)", deadline.toString());

        deadline.markDone();
        assertEquals("[D] [X] return book (by: Jun 06 2023)", deadline.toString());

        deadline.markNotDone();
        assertEquals("[D] [ ] return book (by: Jun 06 2023)", deadline.toString());
    }
}
