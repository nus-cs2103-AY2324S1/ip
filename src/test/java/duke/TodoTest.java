package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {

    @Test
    public void cannotCreateEmptyTodo() {
        assertThrows(DukeException.class, () -> {
            ToDo todo = ToDo.create("");
        });
    }

}

