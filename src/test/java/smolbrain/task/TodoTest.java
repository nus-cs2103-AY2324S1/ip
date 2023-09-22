package smolbrain.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void newTodoTest1() {
        String descr = "[T][ ][0] todo1description";
        assertEquals(descr, new Todo("todo1description").toString());
    }

    @Test
    public void newTodoTest2() {
        String descr = "[T][ ][0] todo2description";
        assertEquals(descr, new Todo("todo2description").toString());
    }

    @Test
    public void markedTodoTest1() {
        String descr = "[T][X][0] todo1description";
        Todo todo = new Todo("todo1description");
        todo.mark();
        assertEquals(descr, todo.toString());
    }

    @Test
    public void markedTodoTest2() {
        String descr = "[T][X][0] todo2description";
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(descr, todo.toString());
    }

    @Test
    public void encodeUnmarkedTodoTest() {
        String descr = "T00todo1description";
        assertEquals(descr, new Todo("todo1description").encode());
    }

    @Test
    public void encodeMarkedTodoTest() {
        String descr = "T10todo2description";
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(descr, todo.encode());
    }

    @Test
    public void findTodoTest1() {
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(false, todo.contain("keyword"));
    }

    @Test
    public void findTodoTest2() {
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(true, todo.contain("todo"));
    }

    @Test
    public void findTodoTest3() {
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(false, todo.contain("Todo"));
    }

    @Test
    public void findTodoTest4() {
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(true, todo.contain("des"));
    }

    @Test
    public void findTodoTest5() {
        Todo todo = new Todo("todo2description");
        todo.mark();
        assertEquals(false, todo.contain("Des"));
    }

    @Test
    public void findTodoTest6() {
        Todo todo = new Todo("my keyword");
        todo.mark();
        assertEquals(true, todo.contain(" "));
    }

    @Test
    public void findTodoTest7() {
        Todo todo = new Todo("my keyword");
        todo.mark();
        assertEquals(true, todo.contain("y k"));
    }

    @Test
    public void findTodoTest8() {
        Todo todo = new Todo("my keyword");
        todo.mark();
        assertEquals(false, todo.contain("y ka"));
    }

    @Test
    public void defaultPriorityTest1() {
        Todo todo = new Todo("myTodo");
        String output = "[T][ ][0] myTodo";
        assertEquals(output, todo.toString());
    }

    @Test
    public void defaultPriorityTest2() {
        Todo todo = new Todo("myTodo");
        String output = "[T][ ][1] myTodo";
        assertNotEquals(output, todo.toString());
    }

    @Test
    public void defaultPriorityTest3() {
        Todo todo = new Todo("myTodo");
        String output = "[T][ ][2] myTodo";
        assertNotEquals(output, todo.toString());
    }

    @Test
    public void defaultPriorityTest4() {
        Todo todo = new Todo("myTodo");
        String output = "[T][ ][3] myTodo";
        assertNotEquals(output, todo.toString());
    }

    @Test
    public void setPriorityTest1() {
        Todo todo = new Todo("myTodo");
        todo.setPriorityLevel(1);
        String output = "[T][ ][1] myTodo";
        assertEquals(output, todo.toString());
    }

    @Test
    public void setPriorityTest2() {
        Todo todo = new Todo("myTodo");
        todo.setPriorityLevel(2);
        String output = "[T][ ][2] myTodo";
        assertEquals(output, todo.toString());
    }

    @Test
    public void setPriorityTest3() {
        Todo todo = new Todo("myTodo");
        todo.setPriorityLevel(3);
        String output = "[T][ ][3] myTodo";
        assertEquals(output, todo.toString());
    }

}
