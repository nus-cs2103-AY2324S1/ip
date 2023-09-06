package ratspeak;

import ratspeak.exception.DukeException;
import ratspeak.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStorageText() {
        try {
            assertEquals("[E][ ] trying books /from 2015-07-09 /to 2017-01-10"
                    , new Event("trying books /from 2015-07-09 /to 2017-01-10").storageText());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
