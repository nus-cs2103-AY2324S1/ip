package joe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import joe.tasks.DeadlineTask;
import joe.tasks.EventTask;
import joe.tasks.TodoTask;

public class TaskListTest {
    @Test
    public void testEmptyListString() {
        assertEquals("No tasks available.", new TaskList().toString());
    }

    @Test
    public void testUnmarkedString() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        tasks.add(new TodoTask("Todo"));
        tasks.add(new DeadlineTask("Deadline", dt));
        tasks.add(new EventTask("Event", dt, dt));
        assertEquals(
                "1.[T][ ] Todo\n"
                        + "2.[D][ ] Deadline (by: 01 Jan 2000 00:00)\n"
                        + "3.[E][ ] Event (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)",
                tasks.toString());
    }

    @Test
    public void testMarkedString() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("Todo");
        DeadlineTask newDeadline = (new DeadlineTask("Deadline", dt));
        EventTask newEvent = (new EventTask("Event", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        newTodo.markAsDone();
        newDeadline.markAsDone();
        newEvent.markAsDone();
        assertEquals("1.[T][X] Todo\n"
                        + "2.[D][X] Deadline (by: 01 Jan 2000 00:00)\n"
                        + "3.[E][X] Event (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)",
                tasks.toString());
    }

    @Test
    public void find_wordExists_success() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("Todo");
        DeadlineTask newDeadline = (new DeadlineTask("Deadline", dt));
        EventTask newEvent = (new EventTask("Event", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("Deadline", false);
        assertEquals("1.[D][ ] Deadline (by: 01 Jan 2000 00:00)", res.toString());
    }

    @Test
    public void find_wordDoesNotExist_noResult() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("Todo");
        DeadlineTask newDeadline = (new DeadlineTask("Deadline", dt));
        EventTask newEvent = (new EventTask("Event", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("adlfhasdlkfh", false);
        assertEquals("No tasks available.", res.toString());
    }

    @Test
    public void find_emptySearchString_allTasks() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("Todo");
        DeadlineTask newDeadline = (new DeadlineTask("Deadline", dt));
        EventTask newEvent = (new EventTask("Event", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("", false);
        assertEquals("1.[T][ ] Todo\n"
                + "2.[D][ ] Deadline (by: 01 Jan 2000 00:00)\n"
                + "3.[E][ ] Event (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)", res.toString());
    }

    @Test
    public void find_randomCase_success() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("all lower case");
        DeadlineTask newDeadline = (new DeadlineTask("ALL UPPER CASE", dt));
        EventTask newEvent = (new EventTask("rAnDoM CaSe", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("cAsE", false);
        assertEquals("1.[T][ ] all lower case\n"
                + "2.[D][ ] ALL UPPER CASE (by: 01 Jan 2000 00:00)\n"
                + "3.[E][ ] rAnDoM CaSe (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)", res.toString());
    }

    @Test
    public void find_matchingCase_success() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("all lower case");
        DeadlineTask newDeadline = (new DeadlineTask("ALL UPPER CASE", dt));
        EventTask newEvent = (new EventTask("rAnDoM CaSe", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("CaSe", true);
        assertEquals("1.[E][ ] rAnDoM CaSe (from: 01 Jan 2000 00:00 to: 01 Jan 2000 00:00)", res.toString());
    }

    @Test
    public void find_matchingCase_noResult() {
        LocalDateTime dt =
                LocalDateTime.parse("01/01/2000 0000", DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasks = new TaskList();
        TodoTask newTodo = new TodoTask("all lower case");
        DeadlineTask newDeadline = (new DeadlineTask("ALL UPPER CASE", dt));
        EventTask newEvent = (new EventTask("rAnDoM CaSe", dt, dt));
        tasks.add(newTodo);
        tasks.add(newDeadline);
        tasks.add(newEvent);
        TaskList res = tasks.findByDesc("cAse", true);
        assertEquals("No tasks available.", res.toString());
    }
}
