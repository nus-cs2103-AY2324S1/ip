package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toStorageString_stringRepresentation_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.toStorageString());
    }

    @Test
    public void toString_stringRepresentation_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void createToDoFromCommand_createToDoObject_success() throws Exception {
        ToDo todo = ToDo.createToDoFromCommand("todo read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void createToDoFromCommand_illegalArgument_exceptionThrown() {
        try {
            ToDo todo = ToDo.createToDoFromCommand("todo");
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createToDoFromStorage_createToDoObject_success() {
        ToDo todo = ToDo.createToDoFromStorage("T | 0 | read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void createToDoFromStorage_createDoneToDoObject_success() {
        ToDo todo = ToDo.createToDoFromStorage("T | 1 | read book");
        assertEquals("[T][X] read book", todo.toString());
    }

}
