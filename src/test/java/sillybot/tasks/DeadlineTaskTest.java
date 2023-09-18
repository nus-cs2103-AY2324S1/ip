package sillybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import sillybot.exceptions.IncompleteInputException;

public class DeadlineTaskTest {
    @Test
    void deadlineTask_encodesCorrectly_1() {
        assertEquals("[D] [ ] return book (by: Jun 10 2024)",
                new DeadlineTask("return book", "2024-06-10").toString());
    }

    @Test
    void deadlineTask_encodesCorrectly_2() {
        assertEquals("[D] [ ] do homework (by: Dec 10 2024)",
                new DeadlineTask("do homework", "2024-12-10").toString());
    }

    @Test
    void deadlineTask_encodesCorrectly_3() {
        assertEquals("[D] [ ] prepare for interview (by: 10 Dec 2024)",
                new DeadlineTask("prepare for interview", "10 Dec 2024").toString());
    }
}
