package Duke.Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void testToStringWithFancyDate() {
        EventTask t = new EventTask("party /from 2003-11-11 1200 /to 2003-11-12 1200");
        assertEquals(t.toString(),"[E] | [ ] | party  | Nov 11 2003 1200 - Nov 12 2003 1200");
    }
}
