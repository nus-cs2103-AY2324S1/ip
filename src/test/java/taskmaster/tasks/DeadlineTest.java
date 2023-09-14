package taskmaster.tasks;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    @Test
    public void create_unmarked_deadline() {
        Deadline deadline = new Deadline ("Project", "2pm","unmarked");
        String result = deadline.toString();
        assertEquals(result, "[D][ ] Project (by: 2pm)");
    }

    @Test
    public void create_marked_deadline() {
        Deadline deadline = new Deadline ("Project", "2pm","marked");
        String result = deadline.toString();
        assertEquals(result, "[D][X] Project (by: 2pm)");
    }
    @Test
    public void check_date() {
        Deadline deadline = new Deadline ("Project", "2019-10-15","marked");
        String result = deadline.toString();
        assertEquals(result, "[D][X] Project (by: Oct 15 2019)");
        LocalDate startDate = deadline.getLocalDate();
        assertTrue(startDate.isAfter(LocalDate.of(2019, 9, 1)));
        assertTrue(startDate.isBefore(LocalDate.of(2019, 11, 3)));
        assertTrue(startDate.isEqual(LocalDate.of(2019, 10, 15)));
    }
}