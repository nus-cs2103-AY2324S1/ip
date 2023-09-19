package kiera.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_userInput_success() {
        Task t = new Task("test1");
        t.markAsDone();
        assertEquals("[ ][X] test1", t.toString());
    }
    @Test
    public void toStorageString() {
        assertEquals("  //   // test1", new Task("test1").toStorageString());
    }
}
