package chatbot;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void addDeleteTasksTest() {
        TaskList taskManager = new TaskList();
        
        assertEquals(0, taskManager.getSize());

        Task todo = new Todo("read book");
        taskManager.add(todo);

        assertEquals(1, taskManager.getSize());
        taskManager.delete(0);
        
        assertEquals(0, taskManager.getSize());
    }

    @Test
    public void markUnmarkTasksTest() {
        TaskList taskManager = new TaskList();
        Task todo_one = new Todo("read book");
        Task todo_two = new Todo("cooking");

        taskManager.add(todo_one);
        taskManager.add(todo_two);

        taskManager.mark(1);
        assertEquals("[T][X] cooking", taskManager.retrieveTask(1).toString());

        taskManager.unmark(1);
        assertEquals("[T][ ] cooking", taskManager.retrieveTask(1).toString());
    }

    @Test
    public void validateInputTest() {
        Parser parser = new Parser();

        try {
            parser.validateInput("deadline", 7);
        } catch (UserInputException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", 
                            e.getMessage());
        }

        try {
            parser.validateInput("event", 6);
        } catch (UserInputException e) {
            assertEquals("OOPS!!! The description of a event cannot be empty.", 
                            e.getMessage());
        }

        try {
            parser.validateInput("todo", 5);
        } catch (UserInputException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", 
                            e.getMessage());
        }
    }
}