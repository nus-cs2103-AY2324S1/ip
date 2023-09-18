package sillybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    void deadlineTask_encodesCorrectly() throws Exception {
        assertEquals("|D| | | return book (by: Jun 10 2024)",
                new DeadlineTask("return book", "2024-06-10").toString());
        assertEquals("|D| | | do homework (by: Dec 10 2024)",
                new DeadlineTask("do homework", "2024-12-10").toString());
    }

}
