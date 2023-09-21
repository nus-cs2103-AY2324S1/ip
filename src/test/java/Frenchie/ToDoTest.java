package Frenchie;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoConstructorTest() {
        ToDo todo = new ToDo("Read Book");
        assertEquals("[T][ ] Read Book", todo.toString());
    }
}