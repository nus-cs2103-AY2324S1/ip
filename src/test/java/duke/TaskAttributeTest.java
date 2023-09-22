package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TaskAttributeTest {

    @Test
    public void get_descAttribute_returnsDesc() {
        TaskAttribute attribute = TaskAttribute.get("description");
        assertEquals(attribute, TaskAttribute.description);
    }

    @Test
    public void get_unknownAttribute_returnsUnknown() {
        TaskAttribute attribute = TaskAttribute.get("");
        assertEquals(attribute, TaskAttribute.unknown);
    }
}
