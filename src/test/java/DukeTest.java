import org.junit.jupiter.api.Test;
import tasks.Deadline;

// package Duke;

public class DukeTest {

    @Test
    public void dummyTest() {
        tasks.Deadline d = new tasks.Deadline("deadline return book /by 2/12/2019 1800", false);
        System.out.println(d.toString());
    }

    @Test
    public void anotherDummyTest() {
        tasks.Event e = new tasks.Event("event project meeting /from Aug 6th 2pm /to 4pm", false);
        System.out.println(e.toString());
    }

}
