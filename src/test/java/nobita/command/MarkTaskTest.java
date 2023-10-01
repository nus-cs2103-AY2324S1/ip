package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.task.ToDo;
import nobita.ui.Ui;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkTaskTest {

    @Test
    public void outOfRangeTest() {
        try {
            MarkCommand command = new MarkCommand(0);

            Ui testUi = new Ui();
            Storage testStorage = new Storage("test");
            TaskList testList = new TaskList();

            testList.addTask(new ToDo("item 1"));
            testList.addTask(new ToDo("item 2"));
            testList.addTask(new ToDo("item 3"));

            NobitaException exception = assertThrows(NobitaException.class, () -> {
                command.execute(testList, testUi, testStorage );
            });

            String expectedMessage = "Selected task number not in list";
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Something went wrong with the test");
        }
    }

    @Test
    public void correctlyMarkTaskTest() {
        try {
            MarkCommand command = new MarkCommand(4);

            Ui testUi = new Ui();
            Storage testStorage = new Storage("test");
            TaskList testList = new TaskList();

            testList.addTask(new ToDo("item 1"));
            testList.addTask(new ToDo("item 2"));
            testList.addTask(new ToDo("item 3"));

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            ToDo testTask = new ToDo("item 4");
            testList.addTask(testTask);

            command.execute(testList, testUi, testStorage );

            String expectedMessage = testTask.toString();
            String actualMessage = testList.getAllTasks().get(4).toString();

            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Something went wrong with the test");
        }
    }
}
