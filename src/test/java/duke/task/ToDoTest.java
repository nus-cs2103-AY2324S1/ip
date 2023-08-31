package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ToDoTest {
    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[T][ ] Test TODO",
                new ToDo("Test TODO").toString());
    }

    @Test
    public void addToDo_success() throws DukeException {
        String description = "New TODO";
        ArrayList<Task> expectedList = new ArrayList<>();
        ToDo testToDo = new ToDo(description);
        expectedList.add(testToDo);
        ArrayList<Task> testList = new ArrayList<>();

        assertEquals(testToDo, ToDo.addTodo(description, testList));
        assertEquals(expectedList, testList);
    }

    @Test
    public void addTodo_descriptionEmpty_exceptionThrown() {
        String description = "";
        ArrayList<Task> testList = new ArrayList<>();

        try {
            assertEquals(new ToDo(description), ToDo.addTodo(description, testList));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.",
                    e.getMessage());
        }

    }
}
