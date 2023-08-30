package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {
    @Test
    public void loadTask_Todo_ValidInput_TaskCreatedAndAddedToList() {
        List<Task> list = new ArrayList<>();
        Storage storage = new Storage("testFilePath");

        String input = "T | 1| Title";
        storage.loadTask(input, list);

        assertEquals(1, list.size());
        assertEquals(Todo.class, list.get(0).getClass());
        assertEquals("Title", list.get(0).getTitle());
        assertEquals(true, list.get(0).getIsMarked());
    }

    @Test
    public void loadTask_Deadline_ValidInput_TaskCreatedAndAddedToList() {
        List<Task> list = new ArrayList<>();
        Storage storage = new Storage("testFilePath");

        String input = "D | 1| Deadline Title| 01/01/2023 14:00";
        storage.loadTask(input, list);

        assertEquals(1, list.size());
        assertEquals(Deadline.class, list.get(0).getClass());
        assertEquals("Deadline Title", list.get(0).getTitle());
        assertEquals(true, list.get(0).getIsMarked());
        assertEquals(LocalDateTime.of(2023, 1, 1, 14, 0), ((Deadline) list.get(0)).getDueDate());
    }

    @Test
    public void loadTask_Event_ValidInput_TaskCreatedAndAddedToList() {
        List<Task> list = new ArrayList<>();
        Storage storage = new Storage("testFilePath");

        String input = "E| 1| Event Title| 01/01/2023 14:00| 01/01/2023 16:00";
        storage.loadTask(input, list);

        assertEquals(1, list.size());
        assertEquals("Event Title", list.get(0).getTitle());
        assertEquals(true,list.get(0).getIsMarked());
        assertEquals(LocalDateTime.of(2023, 1, 1, 14, 0), ((Event) list.get(0)).getFrom());
        assertEquals(LocalDateTime.of(2023, 1, 1, 16, 0), ((Event) list.get(0)).getTo());
    }

    @Test
    public void loadTask_InvalidInput_NoTaskAddedToList() {
        List<Task> list = new ArrayList<>();
        Storage storage = new Storage("testFilePath");

        String input = "InvalidInput";
        storage.loadTask(input, list);

        assertEquals(0, list.size());
    }
}
