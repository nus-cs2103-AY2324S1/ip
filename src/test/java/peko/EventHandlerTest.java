package peko;

import org.junit.jupiter.api.Test;
import peko.exceptions.InvalidTaskException;
import peko.tasks.Event;

public class EventHandlerTest {

    @Test
    public void sameTaskTest() throws InvalidTaskException {
        Event e1 = new Event("Book /from 2/2/2020 1920 /to 3/2/2020 1920");
        Event e2 = new Event("Book /from 2/2/2020 1920 /to 3/2/2020 1920");
        assert e1.equals(e2);
    }
    @Test
    public void sameTaskTest3() throws InvalidTaskException {
        Event e1 = new Event("Book /from 2/2/2020 1920 /to 3/2/2020 1920");
        Event e2 = new Event("Book /from 2/2/2020 1920 /to 4/2/2020 1920");
        assert !e1.equals(e2);
    }
}