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

    @Test
    public void edit_todo() {
        TaskList taskList = makeTestEditTaskList();

        taskList.edit(1, "/a aaa /c ccc /f fff /g ggg /h hhh /i iii /j jjj /k kkk /l lll /m mmm "
                        + "/n nnn /o ooo /p ppp /q qqq /r rrr /t ttt /u uuu /v vvv /w www /x xxx /y yyy /z zzz");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(1, "/d success /b extra1 /s extra2 /e extra3");
        assertEquals(String.format(listHeader
                        + "1.[T][X] success"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(2, "/d success /a 2 /b extra1 /s extra2 /e extra3");
        assertEquals(String.format(listHeader
                        + "1.[T][X] success"
                        + "%n2.[T][ ] success /a 2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());
    }

    @Test
    public void edit_deadline() {
        TaskList taskList = makeTestEditTaskList();

        taskList.edit(3, "/a aaa /c ccc /f fff /g ggg /h hhh /i iii /j jjj /k kkk /l lll /m mmm "
                + "/n nnn /o ooo /p ppp /q qqq /r rrr /t ttt /u uuu /v vvv /w www /x xxx /y yyy /z zzz");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(3, "/d success /b 2023-01-02 12:34 /s extra2 /e extra3");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] success (by: Mon 02 Jan 2023 12:34)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(4, "/d success /a 2 /s extra2 /e extra3");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] success (by: Mon 02 Jan 2023 12:34)"
                        + "%n4.[D][ ] success /a 2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(4, "/b 2023-02-03 23:45 /s extra2 /e extra3");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] success (by: Mon 02 Jan 2023 12:34)"
                        + "%n4.[D][ ] success /a 2 (by: Fri 03 Feb 2023 23:45)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());
    }

    @Test
    public void edit_event() {
        TaskList taskList = makeTestEditTaskList();

        taskList.edit(5, "/a aaa /c ccc /f fff /g ggg /h hhh /i iii /j jjj /k kkk /l lll /m mmm "
                + "/n nnn /o ooo /p ppp /q qqq /r rrr /t ttt /u uuu /v vvv /w www /x xxx /y yyy /z zzz");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] e1 (from: Fri 03 Mar 2023 11:11 to: Fri 03 Mar 2023 22:22)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(5, "/d success /b extra1 /s 2023-03-04 12:34 /e 2023-03-04 23:45");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] success (from: Sat 04 Mar 2023 12:34 to: Sat 04 Mar 2023 23:45)"
                        + "%n6.[E][ ] e2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(6, "/d success /a 2");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] success (from: Sat 04 Mar 2023 12:34 to: Sat 04 Mar 2023 23:45)"
                        + "%n6.[E][ ] success /a 2 (from: Tue 04 Apr 2023 11:11 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(6, "/s 2023-04-05 00:00");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] success (from: Sat 04 Mar 2023 12:34 to: Sat 04 Mar 2023 23:45)"
                        + "%n6.[E][ ] success /a 2 (from: Wed 05 Apr 2023 00:00 to: Tue 04 Apr 2023 22:22)"),
                taskList.toString());

        taskList.edit(6, "/e 2023-04-05 00:01");
        assertEquals(String.format(listHeader
                        + "1.[T][X] t1"
                        + "%n2.[T][ ] t2"
                        + "%n3.[D][X] d1 (by: Sun 01 Jan 2023 11:11)"
                        + "%n4.[D][ ] d2 (by: Thu 02 Feb 2023 22:22)"
                        + "%n5.[E][X] success (from: Sat 04 Mar 2023 12:34 to: Sat 04 Mar 2023 23:45)"
                        + "%n6.[E][ ] success /a 2 (from: Wed 05 Apr 2023 00:00 to: Wed 05 Apr 2023 00:01)"),
                taskList.toString());
    }

    // Used to make a standard tasklist to test edit() on
    private static TaskList makeTestEditTaskList() {
        String fileName = "test_edit.txt";
        Storage storage = new Storage(fileName);
        String data = "TODO || X || t1 || || ||\n"
                + "TODO ||   || t2 || || ||\n"
                + "DEADLINE || X || d1 || 2023-01-01 11:11 || ||\n"
                + "DEADLINE ||  || d2 || 2023-02-02 22:22 || ||\n"
                + "EVENT || X || e1 ||  || 2023-03-03 11:11 || 2023-03-03 22:22\n"
                + "EVENT ||  || e2 ||  || 2023-04-04 11:11 || 2023-04-04 22:22\n";
        storage.save(data);
        return new TaskList(fileName);
    }
}
