package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_correctFormat(){
        ToDo toDo = new ToDo("abc");
        assertEquals(toDo.toString(), "  [T] [ ] abc");
    }

    @Test
    public void create_newToDoInstance(){
        ToDo toDo = new ToDo("abc");
        ToDo toDo1 = ToDo.create("0", "abc");
        assertEquals(toDo.toString(), toDo1.toString());
    }


    @Test
    public void saveToFileLine_correctFormat(){
        ToDo toDo = new ToDo("abc");
        String saveToFileLine = toDo.saveToFileLine();
        assertEquals(saveToFileLine, "T | 0 | abc\n");
    }
}
