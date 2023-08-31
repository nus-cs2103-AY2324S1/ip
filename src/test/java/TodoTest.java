import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void ToDoStringConversion(){
        String taskName = "return book" ;
        assertEquals("[T] [ ] return book", new Todo(taskName).toString());
    }
}
