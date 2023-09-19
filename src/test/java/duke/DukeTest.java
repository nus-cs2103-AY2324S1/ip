package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void duke_initialisation_success() {
        Duke duke = new Duke();
        assertEquals(2, 2);
    }
}