package cyrus.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CommandTypeTest {
    @Test
    public void testFromString() {
        var expected = new HashMap<String, CommandType>();
        String[] testStrings = {"bye", "todo", "deadline", "event", "delete", "list", "mark", "unmark", "nonsense"};
        expected.put("bye", CommandType.BYE);
        expected.put("todo", CommandType.ADD_TODO);
        expected.put("deadline", CommandType.ADD_DEADLINE);
        expected.put("event", CommandType.ADD_EVENT);
        expected.put("delete", CommandType.DELETE_TASK);
        expected.put("list", CommandType.LIST_TASKS);
        expected.put("mark", CommandType.MARK_TASK);
        expected.put("unmark", CommandType.UNMARK_TASK);
        expected.put("nonsense", CommandType.UNKNOWN);

        for (var test : testStrings) {
            assertEquals(expected.get(test), CommandType.fromString(test));
        }
    }
}
