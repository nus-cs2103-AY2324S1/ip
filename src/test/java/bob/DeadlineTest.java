package bob;

import bob.exception.MissingDatesException;
import bob.task.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        try {
            Deadline dl = new Deadline("submit homework /by 2022-01-01");
            assertEquals("[D][ ] submit homework (by: Jan 01 2022)", dl.toString());
        } catch (MissingDatesException e) {
            System.out.println(e.message);
        }
    }

    @Test
    void toTxt() {
        try {
            Deadline dl = new Deadline("submit homework /by 2022-01-01");
            assertEquals("deadline | 0 | submit homework | 2022-01-01", dl.toTxt());
        } catch (MissingDatesException e) {
            System.out.println(e.message);
        }
    }
}

