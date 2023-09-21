package duke;

import duke.task.TaskPriority;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void todoTaskTest1() {
        TodoTask td = new TodoTask("test1", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.LOW);
        assertEquals( "[T][ ][L] test1 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest2() {
        TodoTask td = new TodoTask("test2", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), true, TaskPriority.LOW);
        assertEquals( "[T][X][L] test2 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest3() {
        TodoTask td = new TodoTask("test3", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.MEDIUM);
        assertEquals( "[T][][M] test3 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest4() {
        TodoTask td = new TodoTask("test4", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), true, TaskPriority.MEDIUM);
        assertEquals( "[T][X][M] test4 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest5() {
        TodoTask td = new TodoTask("test5", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.HIGH);
        assertEquals( "[T][][H] test2 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }

    @Test
    public void todoTaskTest6() {
        TodoTask td = new TodoTask("test6", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), true, TaskPriority.HIGH);
        assertEquals( "[T][X][H] test2 (from: Oct 15 2019 to: Dec 01 2020)", td.toString());
    }
}
