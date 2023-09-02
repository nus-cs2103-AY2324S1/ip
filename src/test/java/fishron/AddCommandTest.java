package fishron;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void execute_addTodo_success() throws FishronException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Command addCommand = parser.parse("todo Buy groceries", taskList);

        addCommand.execute(taskList, new Ui(), new Storage("./data/fishron.txt"));

        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Buy groceries", taskList.getTask(1).toString());
    }
}