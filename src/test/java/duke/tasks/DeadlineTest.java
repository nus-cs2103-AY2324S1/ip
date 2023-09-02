package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void deadlineTask_encodesCorrectly() throws Exception {
        assertEquals("|D| | | return book (by: Jun 10 2024)",
                new Deadline("return book", "2024-06-10").toString());
        assertEquals("|D| | | do homework (by: Dec 10 2024)",
                new Deadline("do homework", "2024-12-10").toString());
    }

}
