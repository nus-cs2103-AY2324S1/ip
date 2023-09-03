package nexus.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodo() {
        Todo todo = new Todo("2103 tut");
        todo.setDone();
        assertEquals("[T][X] 2103 tut", todo.toString());
    }

    @Test
    public void testTodoStorage() {
        Todo todo = new Todo("2103 tut");
        todo.setDone();
        assertEquals("T|1|2103 tut", todo.toStorageString());
    }

    // think this does not count because it depends on Parser class
//    @Test
//    public void testEmptyTodo() {
//        String path = "src" + File.separator + "test" + File.separator + "data" + File.separator + "nexus.txt";
//        assertThrows(InvalidInputException.class,
//                () -> Parser.parseInput(new Ui(), new Storage(path), new TaskList(),"todo"));
//    }
}
