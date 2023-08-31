package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void outputTest(){
        assertEquals((new ToDo("study for CS2103T")).toString(), "[T][ ] study for CS2103T");
    }
    @Test
    public void saveStringTest(){
        assertEquals((new ToDo("study for CS2103T")).toSaveString(), "T | 0 | study for CS2103T");
    }

}