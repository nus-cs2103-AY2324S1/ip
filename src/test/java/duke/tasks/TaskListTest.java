package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void sort_emptyList_nothingHappens() {
        TaskList tasks = new TaskList();
        tasks.sort(TaskList.SortOrder.TASK_TYPE);
        tasks.sort(TaskList.SortOrder.TASK_DATE);
        tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);

        assertEquals(0, tasks.getTaskCount());
    }

    @Test
    public void sort_identicalTasks_nothingChanges() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("2023-01-01 00:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2023-01-01 12:00", formatter);

        String d = "description";

        EventTask task0 = new EventTask(d, date1, date2);
        EventTask task1 = new EventTask(d, date1, date2);
        EventTask task2 = new EventTask(d, date1, date2);
        EventTask task3 = new EventTask(d, date1, date2);
        EventTask task4 = new EventTask(d, date1, date2);

        tasks.addTask(task0);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);

        tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);
        tasks.sort(TaskList.SortOrder.TASK_TYPE);
        tasks.sort(TaskList.SortOrder.TASK_DATE);
        tasks.sort(TaskList.SortOrder.TASK_TYPE);
        tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);
        tasks.sort(TaskList.SortOrder.TASK_DATE);

        assertEquals(task0, tasks.getTask(0));
        assertEquals(task1, tasks.getTask(1));
        assertEquals(task2, tasks.getTask(2));
        assertEquals(task3, tasks.getTask(3));
        assertEquals(task4, tasks.getTask(4));
    }

    @Test
    public void sort_sortTaskDescriptions_sortedLexicographically() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("2023-01-01 00:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2023-01-01 12:00", formatter);
        LocalDateTime date3 = LocalDateTime.parse("2023-01-01 23:59", formatter);

        ToDoTask task0 = new ToDoTask(" ");
        ToDoTask task1 = new ToDoTask("read book");
        DeadlineTask task2 = new DeadlineTask("read book", date1);
        EventTask task3 = new EventTask("read book", date1, date2);
        ToDoTask task4 = new ToDoTask("a A");
        ToDoTask task5 = new ToDoTask("a");
        DeadlineTask task6 = new DeadlineTask("12345 hello", date3);
        EventTask task7 = new EventTask("Read book", date2, date3);

        tasks.addTask(task0);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
        tasks.addTask(task5);
        tasks.addTask(task6);
        tasks.addTask(task7);

        tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);

        assertEquals(task0, tasks.getTask(0));
        assertEquals(task6, tasks.getTask(1));
        assertEquals(task7, tasks.getTask(2));
        assertEquals(task5, tasks.getTask(3));
        assertEquals(task4, tasks.getTask(4));
        assertEquals(task1, tasks.getTask(5));
        assertEquals(task2, tasks.getTask(6));
        assertEquals(task3, tasks.getTask(7));
    }

    @Test
    public void sort_sortTaskDate_sortedByDate() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("0001-01-01 00:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2023-01-01 12:00", formatter);
        LocalDateTime date3 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date4 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date5 = LocalDateTime.parse("2023-01-02 23:59", formatter);

        String d = "description";

        ToDoTask task0 = new ToDoTask(d);
        DeadlineTask task1 = new DeadlineTask(d, date4);
        DeadlineTask task2 = new DeadlineTask(d, date4);
        ToDoTask task3 = new ToDoTask(d);
        EventTask task4 = new EventTask(d, date2, date5);
        ToDoTask task5 = new ToDoTask(d);
        EventTask task6 = new EventTask(d, date1, date3);
        DeadlineTask task7 = new DeadlineTask(d, date1);

        tasks.addTask(task0);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
        tasks.addTask(task5);
        tasks.addTask(task6);
        tasks.addTask(task7);

        tasks.sort(TaskList.SortOrder.TASK_DATE);

        assertEquals(task6, tasks.getTask(0));
        assertEquals(task7, tasks.getTask(1));
        assertEquals(task4, tasks.getTask(2));
        assertEquals(task1, tasks.getTask(3));
        assertEquals(task2, tasks.getTask(4));
        assertEquals(task0, tasks.getTask(5));
        assertEquals(task3, tasks.getTask(6));
        assertEquals(task5, tasks.getTask(7));
    }

    @Test
    public void sort_sortTaskType_sortedByType() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("0001-01-01 00:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2023-01-01 12:00", formatter);
        LocalDateTime date3 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date4 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date5 = LocalDateTime.parse("2023-01-02 23:59", formatter);

        String d = "description";

        ToDoTask task0 = new ToDoTask(d);
        DeadlineTask task1 = new DeadlineTask(d, date4);
        DeadlineTask task2 = new DeadlineTask(d, date4);
        ToDoTask task3 = new ToDoTask(d);
        EventTask task4 = new EventTask(d, date2, date5);
        ToDoTask task5 = new ToDoTask(d);
        EventTask task6 = new EventTask(d, date1, date3);
        DeadlineTask task7 = new DeadlineTask(d, date1);

        tasks.addTask(task0);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
        tasks.addTask(task5);
        tasks.addTask(task6);
        tasks.addTask(task7);

        tasks.sort(TaskList.SortOrder.TASK_TYPE);

        assertEquals(task0, tasks.getTask(0));
        assertEquals(task3, tasks.getTask(1));
        assertEquals(task5, tasks.getTask(2));
        assertEquals(task1, tasks.getTask(3));
        assertEquals(task2, tasks.getTask(4));
        assertEquals(task7, tasks.getTask(5));
        assertEquals(task4, tasks.getTask(6));
        assertEquals(task6, tasks.getTask(7));
    }

    @Test
    public void sort_sortSeveralTimes_sortedCorrectly() {
        TaskList tasks = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("0001-01-01 00:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("2023-01-01 12:00", formatter);
        LocalDateTime date3 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date4 = LocalDateTime.parse("2023-01-01 23:59", formatter);
        LocalDateTime date5 = LocalDateTime.parse("2023-01-02 23:59", formatter);

        ToDoTask task0 = new ToDoTask("1234");
        DeadlineTask task1 = new DeadlineTask("hello", date4);
        DeadlineTask task2 = new DeadlineTask("42069", date4);
        ToDoTask task3 = new ToDoTask("read books");
        EventTask task4 = new EventTask("1234", date2, date5);
        ToDoTask task5 = new ToDoTask("Read books");
        EventTask task6 = new EventTask("sdsdsads", date1, date3);
        DeadlineTask task7 = new DeadlineTask("!!!!!!", date1);

        tasks.addTask(task0);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
        tasks.addTask(task5);
        tasks.addTask(task6);
        tasks.addTask(task7);

        tasks.sort(TaskList.SortOrder.TASK_TYPE);
        tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);
        tasks.sort(TaskList.SortOrder.TASK_DATE);

        assertEquals(task7, tasks.getTask(0));
        assertEquals(task6, tasks.getTask(1));
        assertEquals(task4, tasks.getTask(2));
        assertEquals(task2, tasks.getTask(3));
        assertEquals(task1, tasks.getTask(4));
        assertEquals(task0, tasks.getTask(5));
        assertEquals(task5, tasks.getTask(6));
        assertEquals(task3, tasks.getTask(7));
    }
}
