package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;

public class TaskListTest {
    private final String listHeader = String.format("Here are the tasks in your list:%n");
    private final String emptyList = "You have no tasks in your list.";

    @Test
    public void add_tasks_success() {
        String fileName = "test_add.txt";
        Storage storage = new Storage(fileName);
        storage.save("");

        StringBuilder expected = new StringBuilder(listHeader);
        TaskList taskList = new TaskList(fileName);
        taskList.add(TaskList.TaskType.TODO, "todo");
        assertEquals(expected.append("1.[T][ ] todo").toString(), taskList.toString());

        taskList.add(TaskList.TaskType.DEADLINE, "deadline", "2023-08-25 23:59");
        expected.append(String.format("%n2.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"));
        assertEquals(expected.toString(), taskList.toString());

        taskList.add(TaskList.TaskType.EVENT, "event", "2023-08-26 23:59", "2023-08-27 23:59");
        expected.append(String.format("%n3.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"));
        assertEquals(expected.toString(), taskList.toString());
    }

    @Test
    public void add_noDescription_exceptionThrown() {
        String fileName = "test_add.txt";
        Storage storage = new Storage(fileName);
        storage.save("");

        TaskList taskList = new TaskList(fileName);
        try {
            taskList.add(TaskList.TaskType.TODO, "");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("The description of a ToDo task cannot be blank.", e.getMessage());
        }
        try {
            taskList.add(TaskList.TaskType.DEADLINE, "", "2023-08-25 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a Deadline task cannot be blank.", e.getMessage());
        }
        try {
            taskList.add(TaskList.TaskType.EVENT, "", "2023-08-26 23:59", "2023-08-27 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("The description of an Event task cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void add_wrongTaskType_exceptionThrown() {
        String fileName = "test_add.txt";
        Storage storage = new Storage(fileName);
        storage.save("");

        TaskList taskList = new TaskList(fileName);
        // Trying to add as TODO
        try {
            taskList.add(TaskList.TaskType.DEADLINE, "deadline");
            fail();
        } catch (Exception e) {
            assertEquals("Only ToDo tasks can be added with just a description.", e.getMessage());
        }
        try {
            taskList.add(TaskList.TaskType.EVENT, "event");
            fail();
        } catch (Exception e) {
            assertEquals("Only ToDo tasks can be added with just a description.", e.getMessage());
        }

        // Trying to add as Deadline
        try {
            taskList.add(TaskList.TaskType.TODO, "todo", "2023-08-26 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("Only Deadline tasks can be added with a description and deadline.",
                    e.getMessage());
        }
        try {
            taskList.add(TaskList.TaskType.EVENT, "event", "2023-08-26 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("Only Deadline tasks can be added with a description and deadline.",
                    e.getMessage());
        }

        // Trying to add as Event
        try {
            taskList.add(TaskList.TaskType.TODO, "todo", "2023-08-26 23:59", "2023-08-27 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("Only Event tasks can be added with a description, start and end date/time.",
                    e.getMessage());
        }
        try {
            taskList.add(TaskList.TaskType.DEADLINE, "todo", "2023-08-26 23:59", "2023-08-27 23:59");
            fail();
        } catch (Exception e) {
            assertEquals("Only Event tasks can be added with a description, start and end date/time.",
                    e.getMessage());
        }
    }

    @Test
    public void delete_tasks_success() {
        String fileName = "test_delete.txt";
        Storage storage = new Storage(fileName);
        String data = "TODO ||   || todo || || ||\n"
                + "DEADLINE ||   || deadline || 2023-08-25 23:59 || ||\n"
                + "EVENT ||   || event ||  || 2023-08-26 23:59 || 2023-08-27 23:59\n";
        storage.save(data);

        TaskList taskList = new TaskList(fileName);
        taskList.delete(1);
        assertEquals(String.format(listHeader
                        + "1.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n2.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());

        taskList.delete(2);
        assertEquals(listHeader
                        + "1.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)",
                taskList.toString());

        taskList.delete(1);
        assertEquals(listHeader + emptyList, taskList.toString());
    }

    @Test
    public void mark_tasks_success() {
        String fileName = "test_mark.txt";
        Storage storage = new Storage(fileName);
        String data = "TODO ||   || todo || || ||\n"
                + "DEADLINE ||   || deadline || 2023-08-25 23:59 || ||\n"
                + "EVENT ||   || event ||  || 2023-08-26 23:59 || 2023-08-27 23:59\n";
        storage.save(data);

        TaskList taskList = new TaskList(fileName);
        taskList.mark(1);
        assertEquals(String.format(listHeader
                        + "1.[T][X] todo"
                        + "%n2.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());

        taskList.mark(2);
        assertEquals(String.format(listHeader
                        + "1.[T][X] todo"
                        + "%n2.[D][X] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());

        taskList.mark(3);
        assertEquals(String.format(listHeader
                        + "1.[T][X] todo"
                        + "%n2.[D][X] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][X] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());
    }

    @Test
    public void unmark_task_success() {
        String fileName = "test_unmark.txt";
        Storage storage = new Storage(fileName);
        String data = "TODO || X || todo || || ||\n"
                + "DEADLINE || X || deadline || 2023-08-25 23:59 || ||\n"
                + "EVENT || X || event ||  || 2023-08-26 23:59 || 2023-08-27 23:59\n";
        storage.save(data);

        TaskList taskList = new TaskList(fileName);
        taskList.unmark(2);
        assertEquals(String.format(listHeader
                        + "1.[T][X] todo"
                        + "%n2.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][X] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());

        taskList.unmark(3);
        assertEquals(String.format(listHeader
                        + "1.[T][X] todo"
                        + "%n2.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());

        taskList.unmark(1);
        assertEquals(String.format(listHeader
                        + "1.[T][ ] todo"
                        + "%n2.[D][ ] deadline (by: Fri 25 Aug 2023 23:59)"
                        + "%n3.[E][ ] event (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.toString());
    }

    @Test
    public void find_tasks_success() {
        String fileName = "test_find.txt";
        Storage storage = new Storage(fileName);
        String data = "TODO || X || homework || || ||\n"
                + "DEADLINE ||   || return book || 2023-08-25 23:59 || ||\n"
                + "EVENT || X || booking ||  || 2023-08-26 23:59 || 2023-08-27 23:59\n";
        storage.save(data);

        TaskList taskList = new TaskList(fileName);
        assertEquals(String.format("Here are the 2 matching tasks in your list:%n"
                        + "2.[D][ ] return book (by: Fri 25 Aug 2023 23:59)%n"
                        + "3.[E][X] booking (from: Sat 26 Aug 2023 23:59 to: Sun 27 Aug 2023 23:59)"),
                taskList.find("book"));
    }
}
