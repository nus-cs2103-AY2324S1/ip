package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringOutput(){
        ToDo toDo = new ToDo("This is a test ToDo.");
        assertEquals(toDo.toString(), "[T][ ] This is a test ToDo.");
    }

    @Test
    public void testConstructor(){
        ToDo toDo = new ToDo("This is a test ToDo.", true);
        assertEquals(toDo.toString(), "[T][X] This is a test ToDo.");
    }
}
