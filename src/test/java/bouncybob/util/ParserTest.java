package bouncybob.util;

import bouncybob.BouncyBob;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {

    @Test
    public void testGetTaskType() {
        assertEquals(BouncyBob.TaskType.TODO, Parser.getTaskType("todo"));
        assertEquals(BouncyBob.TaskType.DEADLINE, Parser.getTaskType("deadline"));
        assertEquals(BouncyBob.TaskType.EVENT, Parser.getTaskType("event"));
    }

    @Test
    public void testGetAction() {
        assertEquals(BouncyBob.Action.MARK, Parser.getAction("mark"));
        assertEquals(BouncyBob.Action.UNMARK, Parser.getAction("unmark"));
        assertEquals(BouncyBob.Action.DELETE, Parser.getAction("delete"));
    }

    @Test
    public void testGetTaskDeadline() {
        assertEquals("homework", Parser.getTaskDeadline("homework /by 2023-08-23 0000"));
    }

    @Test
    public void testGetTaskEvent() {
        assertEquals("birthday party", Parser.getTaskEvent("birthday party /from 2023-08-23 0800 /to 2023-08-23 1800"));
    }

    @Test
    public void testRemoveAction() {
        String[] input = {"mark", "0"};
        assertEquals("0", Parser.removeAction(input));
    }

    @Test
    public void testExtractDatetime() {
        assertEquals("2023-08-23 0800", Parser.extractDatetime("taskName /by 2023-08-23 0800"));
    }

    @Test
    public void testExtractFromTo() {
        String[] expected = {"2023-08-23 0800", "2023-08-23 1800"};
        assertArrayEquals(expected, Parser.extractFromTo("birthday party /from 2023-08-23 0800 /to 2023-08-23 1800"));
    }
}
