package LogicHandlers.CommandHandlers;

import Models.TaskArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoHandlerTest {
    private TaskArray taskArray;
    private TodoHandler todoHandler;


    @BeforeEach
    public void setUp() {
        taskArray = new TaskArray();
        todoHandler = new TodoHandler(taskArray);
    }
    @Test
    public void parseCommandContentTest1() {
        String commandContent = "Buy groceries";

        String expectedOutput = "Got it, I've added this task: \n" +
                "[T][ ] Buy groceries\n" +
                "You now have 1 tasks in the list.";

        assertEquals(expectedOutput, todoHandler.parseCommandContent(commandContent));
    }

    @Test
    public void parseCommandContentTest2() {
        String commandContent = "";

        String expectedOutput = "You cannot add an empty 'ToDo' task!";

        assertEquals(expectedOutput, todoHandler.parseCommandContent(commandContent));
    }
}
