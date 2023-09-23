package penguin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {

    @Test
    public void getSaveDisplay_success() throws Exception {
        assertEquals("E | 0 | Eat fishes | 2023-08-24 | 2023-08-25",
                new Event("Eat fishes", "2023-08-24", "2023-08-25").getSaveDisplay());
    }
    @Test
    public void newEvent_exceptionThrown() {
        try {
            assertEquals("E | 0 | Eat fishes | 2023-08-24 | 2023-08-25",
                    new Event("Eat fishes", "Aug 24 2023", "Aug 25 2023").getSaveDisplay());
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Aug 24 2023' could not be parsed at index 0", e.getMessage());
        }
    }

}

