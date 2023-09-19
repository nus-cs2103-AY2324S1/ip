package sillybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    void deadlineTask_encodesCorrectly_one() {
        assertEquals("[D] [ ] return book (by: Jun 10 2024)",
                new DeadlineTask("return book", "2024-06-10").toString());
    }

    @Test
    void deadlineTask_encodesCorrectly_two() {
        assertEquals("[D] [ ] do homework (by: Dec 10 2024)",
                new DeadlineTask("do homework", "2024-12-10").toString());
    }

    @Test
    void deadlineTask_encodesCorrectly_three() {
        assertEquals("[D] [ ] prepare for interview (by: 10 Dec 2024)",
                new DeadlineTask("prepare for interview", "10 Dec 2024").toString());
    }
}
