package duke;

import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void todoTaskTest1() {
        TodoTask td = new TodoTask("test1", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false);
        assertEquals( "[T][ ] test1 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest2() {
        TodoTask td = new TodoTask("test2", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), true);
        assertEquals( "[T][X] test2 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }
}
