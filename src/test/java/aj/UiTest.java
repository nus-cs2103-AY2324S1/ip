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
        Task testTodoTask = parser.getTodoTask(remaining, false);
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
    public void checkMessage_correctInput_noExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        String userInput = "todo return book\n";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        boolean haveException = false;
        try {
            testUi.checkMessage(command, remaining);
        } catch (EmptyDescriptionException e) {
            haveException = true;
        } finally {
            assertFalse(haveException);
        }
    }

    @Test
    public void checkMessage_incorrectInput_ExceptionThrown() {
        TaskList testTaskList = createTestTaskList();
        Ui testUi = new Ui(testTaskList);
        String userInput = "todo\n";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        boolean haveException = false;
        try {
            testUi.checkMessage(command, remaining);
        } catch (EmptyDescriptionException e) {
            haveException = true;
        } finally {
            assertTrue(haveException);
        }
    }
}
