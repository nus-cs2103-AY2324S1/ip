package penguin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void getDisplay_success() throws Exception {
        assertEquals("[D][ ]Eat fishes by Aug 24 2023",
                new Deadline("Eat fishes", "2023-08-24").getDisplay());
    }

    @Test
    public void newDeadline_exceptionThrown() {
        try {
            assertEquals("[D][ ]Eat fishes by Aug 24 2023",
                    new Deadline("Eat fishes", "Aug 24 2023").getDisplay());
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Aug 24 2023' could not be parsed at index 0", e.getMessage());
        }
    }

}

