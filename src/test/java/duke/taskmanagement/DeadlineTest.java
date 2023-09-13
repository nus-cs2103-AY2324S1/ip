package duke.taskmanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void todoTest1() {
        Task todo = new Deadline("eat", "2022-03-23",  false);
        assertEquals(todo.toString(),"[D][ ] eat (by: 2022-03-23)");
    }

    @Test
    public void todoTest2() {
        Deadline todo = new Deadline("test", "2022-03-23",false);
        assertEquals(todo.convertIsDone(), "0");
    }
}