package helpbuddy.storage;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.Deadline;
import helpbuddy.task.Event;
import helpbuddy.task.Task;
import helpbuddy.task.ToDo;
import helpbuddy.ui.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    @Test
    public void readEntry_success_1() {
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
    public void readEntry_success_2() {
        try {
            Storage s = new Storage("data/tasks.txt");
            Task actualTask = s.readEntry("D|0|read|2023-08-31T18:00");
            Deadline expectedTask = new Deadline("read", LocalDateTime.parse("31/08/23 18:00", FORMATTER));
            assertEquals(true, expectedTask.equals(actualTask));
        } catch (HelpBuddyException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void readEntry_success_3() {
        try {
            Storage s = new Storage("data/tasks.txt");
            Task actualTask = s.readEntry("E|0|read|2023-08-31T18:00 to 2023-08-31T19:00");
            Event expectedTask = new Event(
                    "read",
                    LocalDateTime.parse("31/08/23 18:00", FORMATTER),
                    LocalDateTime.parse("31/08/23 19:00", FORMATTER)
            );
            assertEquals(true, expectedTask.equals(actualTask));
        } catch (HelpBuddyException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }
}
