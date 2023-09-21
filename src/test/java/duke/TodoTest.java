package duke;

import gman.Parser;
import gman.TaskList;
import gman.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo(" have fun");
        assert todo.toString().equals("[T][ ] have fun");
    }

    @Test
    public void testToString2() {
        Todo todo = new Todo(" have fun");
        Assertions.assertEquals("[T][ ] have fun", todo.toString());
    }

    @Test
    public void testReadFromFile() {
        String textFromFile = "T | O |  have fun";
        String[] segments = textFromFile.split(" \\| ");
        Todo todoToTest = Todo.readFromFile(segments);
        Todo correctTodo =  new Todo(" have fun");
        Assertions.assertEquals(correctTodo.toString(), todoToTest.toString());
    }

    @Test
    public void testStringToWrite() {
        Todo todo = new Todo(" have fun");
        Assertions.assertEquals("T | O |  have fun", todo.stringToWrite());
    }

    @Test
    public void emptyTodoTest() {
        Assertions.assertEquals(Parser.makeTodoCommand("todo", new TaskList()),
                "OOOOOPs! The description of a todo cannot be empty!");
    }
}
