package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void doneTest() {
        ToDo tsk = new ToDo("sleep");
        tsk.setCompleted();
        assertEquals("[X] ", tsk.getCheckbox());
    }
}
