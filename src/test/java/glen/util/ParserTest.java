package glen.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parseInput_shouldHandleMarkCommand() {
        Storage storage = new Storage("test/tasks.txt");

        storage.addTask("T | 0 | Test Task");

        TaskList taskList = storage.read();
        Parser parser = new Parser(storage, taskList);

        String result = parser.parseInput("mark 1");
        assertEquals("_____________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "  [X] Test Task\n" +
                "_____________________________________________________\n", result);

        // Validate that the task is marked as done
        assertTrue(taskList.lst().contains("[X] Test Task"));

        // reset test/tasks.txt to an empty file.
        storage.updateTask(0, null);
    }

}
