import functional.DukeException;
import org.junit.jupiter.api.Test;

// package duke.Duke;

public class DukeTest {

    @Test
    public void dummyTest() throws DukeException {
        tasks.Deadline d = new tasks.Deadline("deadline return book /by 2/12/2019 1800", false);
        System.out.println(d.toString());
    }

    @Test
    public void anotherDummyTest() throws DukeException {
        tasks.Event e = new tasks.Event("event project meeting /from Aug 6th 2pm /to 4pm", false);
        System.out.println(e.toString());
    }

}
