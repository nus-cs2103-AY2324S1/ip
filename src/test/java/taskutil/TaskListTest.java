package taskutil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskListTest {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    LocalDateTime dateTime = LocalDateTime.parse("12.04.2020 1530", format);
    Deadline task2 = new Deadline("Finish homework", dateTime);
    Event task3 = new Event("Japan trip", dateTime, dateTime);
    Todo task1 = new Todo("Buy food");

    @Test
    public void addTask_deleteTask_Success() {
        TaskList tasks = new TaskList();
        tasks.addTask(task2, false);

        assertEquals("D |   | Finish homework | 12.04.2020 1530\n", tasks.listToStringData());

        tasks.deleteTask(0);

        assertEquals("", tasks.listToStringData());
    }

    @Test
    public void addTask_changeStatus_listToStringData_correctOutput() {
        TaskList tasks = new TaskList();
        task1.changeStatus(true);
        tasks.addTask(task1, false);
        tasks.addTask(task2, false);
        tasks.addTask(task3, false);

        String expected = "T | X | Buy food\n"
                        + "D |   | Finish homework | 12.04.2020 1530\n"
                        + "E |   | Japan trip | 12.04.2020 1530 | 12.04.2020 1530\n";

        assertEquals(expected, tasks.listToStringData());
    }
}
