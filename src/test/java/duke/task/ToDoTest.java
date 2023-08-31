package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    /**
     * Checks if toString method returns string with correct format.
     */
    @Test
    public void toString_correctFormat(){
        ToDo toDo = new ToDo("abc");
        assertEquals(toDo.toString(), "  [T] [ ] abc");
    }

    /**
     * Checks if create method returns correct to do task.
     */
    @Test
    public void create_newToDoInstance(){
        ToDo toDo = new ToDo("abc");
        ToDo toDo1 = ToDo.create("0", "abc");
        assertEquals(toDo.toString(), toDo1.toString());
    }


    /**
     * Checks if saveToFileLine method returns string with correct format.
     */
    @Test
    public void saveToFileLine_correctFormat(){
        ToDo toDo = new ToDo("abc");
        String saveToFileLine = toDo.saveToFileLine();
        assertEquals(saveToFileLine, "T | 0 | abc\n");
    }
}
