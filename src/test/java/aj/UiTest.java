package aj;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    public TaskList createTestTaskList() {
        List<Task> taskList = new ArrayList<>();
        String userInput = "todo return book";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        Parser parser = new Parser();
        Task testTodoTask = parser.getTodoTask(remaining,
                false);
        taskList.add(testTodoTask);
        return new TaskList(taskList);
    }

    @Test
    public void checkIndex_correctIndex_noExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        boolean haveException = false;
        try {
            testUi.checkIndex(0);
        } catch (IndexOutOfRangeException e) {
            haveException = true;
        } finally {
            assertFalse(haveException);
        }
    }

    @Test
    public void checkIndex_incorrectIndex_ExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        boolean haveException = false;
        try {
            testUi.checkIndex(1);
        } catch (IndexOutOfRangeException e) {
            haveException = true;
        } finally {
            assertTrue(haveException);
        }
    }

    @Test
    public void checkCommand_wrongInput_EmptyDescriptionExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        String userInput = "todo";
        boolean isCorrect = false;
        try {
            testUi.checkCommand(userInput);
        } catch (AjException e) {
            if (e instanceof EmptyDescriptionException) {
                isCorrect = true;
            }
        } finally {
            assertTrue(isCorrect);
        }
    }

    @Test
    public void checkCommand_wrongInput_NoSuchCommandExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        String userInput = "deadline wrong command"; // no 'by' flag
        boolean isCorrect = false;
        try {
            testUi.checkCommand(userInput);
        } catch (AjException e) {
            if (e instanceof NoSuchCommandException) {
                isCorrect = true;
            }
        } finally {
            assertTrue(isCorrect);
        }
    }
}
