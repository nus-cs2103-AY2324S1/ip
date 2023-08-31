package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addToDo_multipleEntries_success() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        tasks.addToDo("todo Midterms".split(" ", 2));
        tasks.addToDo("todo Finals".split(" ", 2));
        // add 2 ToDos to the list
        assertEquals("T | N | Midterms\n" + "T | N | Finals\n", listToString(list));
    }

    @Test
    public void addToDo_emptyEntry_notAdded() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        // todo should be followed by description
        tasks.addToDo("todo".split(" ", 2));

        // todo description cannot be empty
        tasks.addToDo("todo     ".split(" ", 2));

        assertEquals("", listToString(list));
    }

    @Test
    public void addDeadline_multipleEntries_success() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        tasks.addDeadline("deadline Midterms /by 2023-10-11".split(" ", 2));
        tasks.addDeadline("deadline Finals /by 2023-11-11".split(" ", 2));
        // add 2 Deadlines to the list
        assertEquals("D | N | Midterms | 2023-10-11\n" + "D | N | Finals | 2023-11-11\n",
               listToString(list));
    }

    @Test
    public void addDeadline_wrongFormat_notAdded() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        // /by should be used
        tasks.addDeadline("deadline Midterms by 2023-10-11".split(" ", 2));

        // description cannot be empty
        tasks.addDeadline("deadline /by 2023-11-11".split(" ", 2));

        // wrong date format
        tasks.addDeadline("deadline Finals /by 11 Oct 2023".split(" ", 2));

        // wrong date format
        tasks.addDeadline("deadline Finals /by 10-10-2023".split(" ", 2));

        assertEquals("", listToString(list));
    }

    @Test
    public void addDeadline_invalidDate_notAdded() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);

        // day cannot exceed 31
        tasks.addDeadline("deadline Finals /by 2023-10-40".split(" ", 2));

        // month cannot exceed 12
        tasks.addDeadline("deadline Finals /by 2023-13-10".split(" ", 2));

        // february 30 is invalid
        tasks.addDeadline("deadline Finals /by 2023-02-30".split(" ", 2));

        // april 31 is invalid
        tasks.addDeadline("deadline Finals /by 2023-04-31".split(" ", 2));

        // month expects 2 digits
        tasks.addDeadline("deadline Finals /by 2023-5-31".split(" ", 2));

        assertEquals("", listToString(list));
    }

    @Test
    public void addEvent_multipleEntries_success() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        tasks.addEvent("event Midterms /from 2023-10-11 /to 2023-10-12".split(" ", 2));
        tasks.addEvent("event Finals /from 2023-11-11 /to 2023-11-12".split(" ", 2));
        // add 2 Events to the list
        assertEquals("E | N | Midterms | 2023-10-11 | 2023-10-12\n"
                        + "E | N | Finals | 2023-11-11 | 2023-11-12\n",
                listToString(list));
    }

    @Test
    public void addEvent_wrongFormat_notAdded() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);
        // /from should be used
        tasks.addEvent("event Midterms from 2023-10-11 /to 2023-10-12".split(" ", 2));

        // /to should be used
        tasks.addEvent("event Midterms /from 2023-10-11 to 2023-10-12".split(" ", 2));

        // description cannot be empty
        tasks.addEvent("event /from 2023-10-11 /to 2023-10-12".split(" ", 2));

        // wrong date format
        tasks.addEvent("event Finals /from Today /to 2023-11-12".split(" ", 2));

        // wrong date format
        tasks.addEvent("event Midterms /from 10 Oct 2023 /to 2023-10-11".split(" ", 2));

        // wrong date format
        tasks.addDeadline("event Finals /from 10-10-2023 /to 11-10-2023".split(" ", 2));

        assertEquals("", listToString(list));
    }

    @Test
    public void addEvent_invalidDate_notAdded() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList tasks = new TaskList(list);

        // month cannot exceed 12
        tasks.addEvent("event Midterms /from 2023-13-11 /to 2023-10-12".split(" ", 2));

        // day cannot exceed 31
        tasks.addEvent("event Finals /from 2023-11-40 /to 2023-11-12".split(" ", 2));

        // february 30 is invalid
        tasks.addEvent("event Midterms /from 2023-02-30 /to 2023-10-12".split(" ", 2));;

        // april 31 is invalid
        tasks.addEvent("event Midterms /from 2023-04-31 /to 2023-10-12".split(" ", 2));

        // month expects 2 digits
        tasks.addEvent("event Midterms /from 2023-1-11 /to 2023-10-12".split(" ", 2));

        assertEquals("", listToString(list));
    }

    public String listToString(ArrayList<Task> list) {
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            toWrite.append(list.get(i).taskToString());
            toWrite.append("\n");
        }
        return toWrite.toString();
    }
}
