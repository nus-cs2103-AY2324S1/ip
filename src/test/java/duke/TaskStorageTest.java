package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskStorage;

public class TaskStorageTest {
    @Test
    public void testFind() {
        TaskStorage taskStorage = new TaskStorage(true);
        taskStorage.save("todo read book");
        taskStorage.save("deadline return book /by 2019-09-06");
        assertEquals("Here are the matching tasks in your list:\n"
                + "1. [T][ ] read book\n"
                + "2. [D][ ] return book (by: Sep 6 2019)\n",
                taskStorage.find("book"));
    }
}
