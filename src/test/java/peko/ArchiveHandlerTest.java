package peko;

import org.junit.jupiter.api.Test;
import peko.exceptions.InvalidTaskException;
import peko.tasks.Task;
import peko.tasks.ToDos;
import peko.memory.*;

public class ArchiveHandlerTest {
    @Test
    public void storeTest() throws InvalidTaskException {
        Task t = new ToDos("ye");
        ArchiveHandler.archive(t);
    }
}
