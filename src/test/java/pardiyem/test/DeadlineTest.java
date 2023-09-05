package pardiyem.test;

import org.junit.jupiter.api.Test;
import pardiyem.task.Deadline;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadlineTest1() {
        assertEquals(new Deadline("Joni", "2015-01-23", true).toString(), "[D][X] Joni (by: 2015-01-23)");
    }

    @Test
    public void deadlineTest2() {
        assertEquals(new Deadline("Joni", "2015-01-23", false).toString(), "[D][ ] Joni (by: 2015-01-23)");
    }

    @Test
    public void deadlineTest3() {
        try {
            new Deadline("Joni", "20353-01-23", true);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Please input your time in the format of either \"YYYY-MM-DD\" or \"YYYY-MM-DD HH:MM:SS\"");
        }
    }
    @Test
    public void deadlineTest4() {
        try {
            new Deadline("Joni", "", true);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Whoops, a deadline needs to have a non-empty do by description");
        }
    }

    @Test
    public void parseDeadlineTest1() {
        assertEquals(Deadline.parseDesc("Joni /by 2015-01-23"), new ArrayList<String>(Arrays.asList("Joni", "2015-01-23")));
    }

    @Test
    public void parseDeadlineTest2() {
        try {
            Deadline.parseDesc("Joni");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Whoops, you forgot to indicate the deadline by using \"/by *insert deadline*\"");
        }
    }
}
