package helpbuddy.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.Deadline;
import helpbuddy.task.Event;
import helpbuddy.task.Task;
import helpbuddy.task.ToDo;

public class StorageTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    @Test
    public void readEntry_success1() {
        try {
            Storage s = new Storage("data/tasks.txt");
            Task actualTask = s.readEntry("T|0|read");
            ToDo expectedTask = new ToDo("read");
            assertEquals(true, expectedTask.equals(actualTask));
        } catch (HelpBuddyException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void readEntry_success2() {
        try {
            Storage s = new Storage("data/tasks.txt");
            Task actualTask = s.readEntry("D|0|read|2023-08-31T18:00");
            Deadline expectedTask = new Deadline("read", LocalDateTime.parse("31/08/23 18:00", formatter));
            assertEquals(true, expectedTask.equals(actualTask));
        } catch (HelpBuddyException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void readEntry_success3() {
        try {
            Storage s = new Storage("data/tasks.txt");
            Task actualTask = s.readEntry("E|0|read|2023-08-31T18:00 to 2023-08-31T19:00");
            Event expectedTask = new Event(
                    "read",
                    LocalDateTime.parse("31/08/23 18:00", formatter),
                    LocalDateTime.parse("31/08/23 19:00", formatter)
            );
            assertEquals(true, expectedTask.equals(actualTask));
        } catch (HelpBuddyException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }
}
