package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-06-06"));
        assertEquals("[D] [ ] return book (by: Jun 06 2023)", deadline.toString());

        deadline.markDone();
        assertEquals("[D] [X] return book (by: Jun 06 2023)", deadline.toString());

        deadline.markNotDone();
        assertEquals("[D] [ ] return book (by: Jun 06 2023)", deadline.toString());
    }
}
