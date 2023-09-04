package ax.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DeadlinesTest {

    @Test
    void setDone() {
        Deadlines temp = new Deadlines("you said when", "2023-10-09");
        temp.setDone(true);
        assertEquals(temp.toString(), "[D] [✅] you said when (by: 2023-10-09)");
        temp.setDone(false);
        assertEquals(temp.toString(), "[D] [ ] you said when (by: 2023-10-09)");
    }


    @Test
    void testToString() {
        Deadlines temp = new Deadlines("you said when", "2023-10-09");
        assertEquals(temp.toString(), "[D] [ ] you said when (by: 2023-10-09)");
    }
}
