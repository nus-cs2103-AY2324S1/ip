import org.junit.jupiter.api.Test;

import functional.DukeException;

public class DukeTest {

    @Test
    public void dummyTest() throws DukeException {
        tasks.Deadline d = new tasks.Deadline("deadline return book /by 2/12/2019 1800", false);
        System.out.println(d.toString());
    }

    @Test
    public void anotherDummyTest() throws DukeException {
        tasks.Event e = new tasks.Event("event project meeting /from 06/08/2023 1400 /to 1600", false);
        System.out.println(e.toString());
    }

}
