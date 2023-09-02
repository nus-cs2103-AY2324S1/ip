package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void toStringTest() {
        LocalDateTime deadline = LocalDateTime.of(2023, 5, 30, 8, 0);
        Deadline task = new Deadline(false, "Bobi Homework ", deadline);
        assertEquals("[D][ ] Bobi Homework (by: May 30 2023 at 08:00)", task.toString());
    }

    @Test
    public void toStoreStringTest() {
        LocalDateTime deadline = LocalDateTime.of(2023, 5, 30, 8, 0);
        Deadline task = new Deadline(false, "Bobi Homework ", deadline);
        assertEquals("D/@/0/@/Bobi Homework /@/2023-05-30T08:00", task.toStoreString());
    }
}
