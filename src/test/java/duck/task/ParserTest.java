package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duck.Parser;
import duck.Storage;
import duck.exceptions.DuckException;
import duck.ui.Ui;

public class ParserTest {

    private final Ui ui = new Ui();
    private Storage storage = new Storage("temp.txt");
    private TaskList taskList = new TaskList();

    @Test
    public void testIdentify_taskCommandInput_taskName() {
        Parser parser = new Parser();
        String command = parser.identify("deadline return book /by 2023-03-03 1220");
        assertEquals("deadline", command);
        command = parser.identify("event attend marraige /from 2023-09-09 1220 /to 2023-09-10 1220");
        assertEquals("event", command);
        command = parser.identify("todo project");
        assertEquals("todo", command);
    }
    @Test
    public void testParse_validListCallWithEmpty_emptyList() {
        Parser parser = new Parser();
        String actual = parser.parse("list", ui, taskList, storage);
        assertEquals("There are no tasks in your lists.", actual);
    }
    @Test
    public void testParse_validListCallWithPopulated_populatedList() {
        Parser parser = new Parser();
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            String actual = parser.parse("list", ui, taskList, storage);
            String expected = "Here are all the tasks in your list: \n"
                + "1.[D][ ] return book (by: Sep 23 2023 12:20)\n"
                + "2.[T][ ] project\n"
                + "3.[T][ ] Assignment\n";
            assertEquals(expected, actual);
        } catch (DuckException e) {
            fail();
        }
    }

    @Test
    public void testParse_validToDoCall_populateList() {
        Parser parser = new Parser();
        try {
            taskList.setListOfTasks(new ArrayList<>());
            ArrayList<Task> expectedTaskList = new ArrayList<>();
            expectedTaskList.add(taskList.setToDo("project"));
            String expected = "Got it. I've added this task:\n"
                + "  [T][ ] project\n"
                + "Now you have 1 tasks in the list.\n";
            String actual = parser.parse("todo project", ui, taskList, storage);
            assertEquals(expected, actual);
            assertEquals(expectedTaskList.get(0).toString(), taskList.getListOfTasks().get(0).toString());
        } catch (DuckException e) {
            fail();
        } finally {
            File temp = new File("temp.txt");
            temp.delete();
        }
    }

    @Test
    public void testParse_validDeadlineCall_populateList() {
        Parser parser = new Parser();
        try {
            taskList.setListOfTasks(new ArrayList<>());
            ArrayList<Task> expectedTaskList = new ArrayList<>();
            expectedTaskList.add(taskList.setDeadline("return book /by 2023-09-23 1220"));
            String expected = "Got it. I've added this task:\n"
                + "  [D][ ] return book (by: Sep 23 2023 12:20)\n"
                + "Now you have 1 tasks in the list.\n";
            String actual = parser.parse("deadline return book /by 2023-09-23 1220", ui, taskList, storage);
            assertEquals(expected, actual);
            assertEquals(expectedTaskList.get(0).toString(), taskList.getListOfTasks().get(0).toString());
        } catch (DuckException e) {
            fail();
        } finally {
            File temp = new File("temp.txt");
            temp.delete();
        }
    }

    @Test
    public void testParse_validEventCall_populateList() {
        Parser parser = new Parser();
        try {
            taskList.setListOfTasks(new ArrayList<>());
            ArrayList<Task> expectedTaskList = new ArrayList<>();
            expectedTaskList.add(taskList.setEvent("gala /from 2023-09-23 1400 /to 2023-09-23 1800"));
            String expected = "Got it. I've added this task:\n"
                + "  [E][ ] gala (from: Sep 23 2023 02:00 PM to: 06:00 PM)\n"
                + "Now you have 1 tasks in the list.\n";
            String actual = parser.parse("event gala /from 2023-09-23 1400 /to 2023-09-23 1800", ui, taskList, storage);
            assertEquals(expected, actual);
            assertEquals(expectedTaskList.get(0).toString(), taskList.getListOfTasks().get(0).toString());
        } catch (DuckException e) {
            fail();
        } finally {
            File temp = new File("temp.txt");
            temp.delete();
        }
    }

}
