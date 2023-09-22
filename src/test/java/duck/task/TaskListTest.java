package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duck.exceptions.DuckException;
import duck.exceptions.SemanticException;
import duck.exceptions.SyntaxException;


public class TaskListTest {
    private TaskList taskList = new TaskList();


    @Test
    public void testSetToDo_whenGivenDescription_todoTaskDescription() throws DuckException {
        String input = "Do ip";
        TaskList test = new TaskList();
        ToDo todo = test.setToDo(input);
        assertEquals("Do ip", todo.getDescription());
    }
    @Test
    public void testSetToDo_whenTaskDescHasEmptySpaces_throwsException() {
        String input = "   ";
        TaskList test = new TaskList();
        assertThrows(DuckException.class, ()->test.setToDo(input));
    }

    @Test
    public void testSetToDo_testWithSpacesAndWords_taskDescription() throws DuckException {
        String input = " project ";
        TaskList test = new TaskList();
        ToDo todo = test.setToDo(input);
        assertEquals("project", todo.getDescription());
    }

    @Test
    public void givenValidDateWithMatchingTasks_whenCheckSchedule_thenEmptyListIsReturned() throws SyntaxException {
        TaskList checkScheduleTest = new TaskList();
        ArrayList<Task> listOfTasks = new ArrayList<>();
        String str = "2023-09-23";

        ArrayList<Task> result = checkScheduleTest.checkSchedule(str);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteTask_normalDescription_correctOutputShown() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("deadline return book /by 2023-09-23 1220"));
            List<Task> task = taskList.getListOfTasks();
            System.out.println(task);
            taskList.deleteTask("delete 1", taskList);
            assertEquals(0, taskList.size());
        } catch (DuckException e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask_stringArg_errorGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            assertThrows(SyntaxException.class, ()->taskList.deleteTask("delete a", taskList));
        } catch (DuckException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void testDeleteTask_indexOutOfBounds_exceptionThrown() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            assertThrows(SemanticException.class, () -> taskList.deleteTask("delete 4", taskList));
        } catch (DuckException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void testDeleteTask_noIndexGiven_exceptionThrown() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            assertThrows(SyntaxException.class, () -> taskList.deleteTask("delete ", taskList));
        } catch (DuckException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void testDeleteTask_negativeIndexGiven_exceptionThrown() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SemanticException.class, () -> taskList.deleteTask("delete -1", taskList));
        } catch (DuckException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void testMarkTask_normalInputGiven_correctOutputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            taskList.markTask(2);
            assertTrue(taskList.get(2).checkDone());
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testMarkTask_negativeInputGiven_correctOutputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SemanticException.class, ()->taskList.markTask(-1));

        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetDone_correctCommand_correctOutputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            taskList.setDone("mark 2", taskList);
            assertTrue(taskList.get(1).checkDone());
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetDone_incorrectCommand_exceptionGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SyntaxException.class, () -> taskList.setDone("unmark 2", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetDone_commandWithSpace_exceptionGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SyntaxException.class, () -> taskList.setDone("mark ", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetDone_negativeInteger_exceptionGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SemanticException.class, () -> taskList.setDone("mark -1", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetDone_stringAsArgument_exceptionGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SyntaxException.class, () -> taskList.setDone("mark x", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetUndone_correctCommad_outputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            taskList.setUndone("unmark 1", taskList);
            assertTrue(!(taskList.get(1).checkDone()));

        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetUndone_stringArgument_outputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SyntaxException.class, () -> taskList.setDone("unmark x", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetUndone_negativeIndexNumber_outputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SemanticException.class, () -> taskList.setUndone("unmark -1", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testSetUndone_spaceGiven_outputGiven() {
        try {
            taskList.setListOfTasks(new ArrayList<>());
            taskList.addTask(taskList.setDeadline("return book /by 2023-09-23 1220"));
            taskList.addTask(taskList.setToDo("project"));
            taskList.addTask(taskList.setToDo("Assignment"));
            assertThrows(SyntaxException.class, () -> taskList.setUndone("unmark ", taskList));
        } catch (DuckException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }





}
