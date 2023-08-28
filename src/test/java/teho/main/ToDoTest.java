package teho.main; //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest(){
        String command = "Eat dinner";
        Task task = new ToDo(command);
        assertEquals("[T][ ] Eat dinner", task.toString());
    }

    @Test
    public void fileStringTest() {
        String command = "Dance";
        Task task = new ToDo(command);
        assertEquals("T|0|Dance", task.fileString());
    }
}

