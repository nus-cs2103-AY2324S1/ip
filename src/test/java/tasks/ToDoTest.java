package tasks;
//CHECKSTYLE.OFF: CustomImportOrder
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//CHECKSTYLE.ON: CustomImportOrder

public class ToDoTest {

    @Test
    public void toDoString() {
        assertEquals("[T][]die", new ToDo("die").toString());
    }


}
