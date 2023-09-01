package smolbrain.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void newTodoTest1() {
        String descr = "[T][ ] todo1description";
        assertEquals(descr, new Todo("todo1description").toString());
    }

    @Test
    public void newTodoTest2() {
        String descr = "[T][ ] todo2description";
        assertEquals(descr, new Todo("todo2description").toString());
    }

    @Test
    public void markedTodoTest1() {
        String descr = "[T][X] todo1description";
        Todo todo = new Todo("todo1description");
        todo.mark();
        assertEquals(descr, todo.toString());
    }

    @Test
    public void markedTodoTest2() {
        String descr = "[T][X] todo2description";
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(descr, todo.toString());
    }

    @Test
    public void encodeUnmarkedTodoTest() {
        String descr = "T0todo1description";
        assertEquals(descr, new Todo("todo1description").encode());
    }

    @Test
    public void encodeMarkedTodoTest() {
        String descr = "T1todo2description";
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(descr, todo.encode());
    }


}
