package duke.task;

import duke.DukeException;
import duke.task.DukeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DukeListTest {
    private DukeList dukeList;

    @BeforeEach
    public void setup() {
        dukeList = new DukeList(new ArrayList<>());
    }

    @Test
    public void testAddTodo() throws DukeException {
        dukeList.addTask("Todo", "cook curry");
        assertEquals(1, dukeList.getList().size());
    }

//    @Test
//    public void testAddDeadline() {
//        LocalDateTime by = LocalDateTime.now().plusDays(1);
//        dukeList.addTask("make tiramisu by );
//        assertEquals(1, dukeList.getList().size());
//    }
//
//    @Test
//    public void testAddEvent() {
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = start.plusHours(2);
//        dukeList.addTask("Team meeting", start, end);
//        assertEquals(1, dukeList.getList().size());
//    }

    @Test
    public void testDeleteTask() throws DukeException {
        dukeList.addTask("Todo", "Do something");
        dukeList.deleteTask(Integer.toString(1));
        assertEquals(0, dukeList.getList().size());
    }

}
