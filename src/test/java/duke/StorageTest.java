package duke;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void testSaveAndLoad() throws IOException {

        Path filePath = Path.of("data/test.txt");
        Storage storage = new Storage(filePath.toString());
        TaskList list = new TaskList();

        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "Monday");
        Event event = new Event("project meeting", "Mon 2pm", "4pm");

        list.add(todo);
        list.add(deadline);
        list.add(event);

        storage.save(list);
        List<String> lines = Files.readAllLines(filePath);

        // Assert
        assertEquals(3, lines.size());
        assertEquals("T | 0 | read book", lines.get(0));
        assertEquals("D | 0 | return book | Monday", lines.get(1));
        assertEquals("E | 0 | project meeting | Mon 2pm | 4pm", lines.get(2));
    }

    @Test
    public void testLoadInvalidFile() {

        String invalidFilePath = "invalid-file-path.txt";
        Storage storage = new Storage(invalidFilePath);

        assertThrows(NullPointerException.class, () -> {
            storage.load();
        });
    }
}
