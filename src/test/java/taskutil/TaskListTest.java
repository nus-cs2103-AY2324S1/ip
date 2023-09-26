package taskutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    private final LocalDateTime dateTime = LocalDateTime.parse("12.04.2020 1530", format);
    private final Deadline task2 = new Deadline("Finish homework", dateTime);
    private final Event task3 = new Event("3 Japan trip", dateTime, dateTime);
    private final Todo task1 = new Todo("Buy food bring home");

    @Test
    public void addTask_deleteTask_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(task2, false);

        Assertions.assertEquals("D |   | Finish homework | 12.04.2020 1530\n", tasks.listToStringData());

        tasks.deleteTask(0);

        Assertions.assertEquals("", tasks.listToStringData());
    }

    @Test
    public void changeStatus_listToStringData_correctOutput() {
        TaskList tasks = new TaskList();
        task1.setStatus(true);
        tasks.addTask(task1, false);
        tasks.addTask(task2, false);
        tasks.addTask(task3, false);

        String expected = "T | X | Buy food bring home\n"
                        + "D |   | Finish homework | 12.04.2020 1530\n"
                        + "E |   | 3 Japan trip | 12.04.2020 1530 | 12.04.2020 1530\n";

        Assertions.assertEquals(expected, tasks.listToStringData());
    }

    @Test
    public void queryList_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1, false);
        tasks.addTask(task2, false);
        tasks.addTask(task3, false);

        String expected1 = "These tasks match your query:\n"
                + "     1.[E][ ] 3 Japan trip (from: 12 Apr 2020, 03:30 PM to: 12 Apr 2020, 03:30 PM)";

        String expected2 = "These tasks match your query:\n"
                + "     1.[T][ ] Buy food bring home\n"
                + "     2.[D][ ] Finish homework (by: 12 Apr 2020, 03:30 PM)";

        Assertions.assertEquals(expected1, tasks.queryList("3"));
        Assertions.assertEquals(expected2, tasks.queryList("home"));
    }

    @Test
    public void schedule_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1, false);
        tasks.addTask(task2, false);
        tasks.addTask(task3, false);

        LocalDateTime dateTime1 = LocalDateTime.parse("11.04.2020 1530", format);
        String expected1 = "These tasks are due for 11.04.2020 1530:\n"
                + "     1.[T][ ] Buy food bring home\n"
                + "     2.[D][ ] Finish homework (by: 12 Apr 2020, 03:30 PM)";
        Assertions.assertEquals(expected1, tasks.filterByTime(dateTime1));

        LocalDateTime dateTime2 = LocalDateTime.parse("13.04.2020 1530", format);
        String expected2 = "These tasks are due for 13.04.2020 1530:\n"
                + "     1.[T][ ] Buy food bring home";
        Assertions.assertEquals(expected2, tasks.filterByTime(dateTime2));
    }
}
