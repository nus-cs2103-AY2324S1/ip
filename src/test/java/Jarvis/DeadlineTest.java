package Jarvis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_correctInitialisation_toStringCorrect() {
        Deadline deadline = new Deadline("make helmet", "2023-09-02");
        assertEquals("[D][ ] make helmet (by: Sep 2 2023)", deadline.toString());
    }
}
