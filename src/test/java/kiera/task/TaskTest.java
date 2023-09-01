package kiera.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
