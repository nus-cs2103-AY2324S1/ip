package leon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_numTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test"));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addCompletedTask_numTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test", true));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addCompletedTask_numCompletedTasksIncremented() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test", true));
        assertEquals(1, tasks.getNumOfCompletedTasks());
    }

    @Test
    public void addTask_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test"));
        assertEquals(TaskList.TaskType.TASK, tasks.getTaskType(0));
    }

    @Test
    public void addToDo_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals(TaskList.TaskType.TODO, tasks.getTaskType(0));
    }

    @Test
    public void addDeadline_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("test", LocalDateTime.MIN));
        assertEquals(TaskList.TaskType.DEADLINE, tasks.getTaskType(0));
    }

    @Test
    public void addEvent_getTaskType_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Event("test", LocalDateTime.MIN, LocalDateTime.MAX));
        assertEquals(TaskList.TaskType.EVENT, tasks.getTaskType(0));
    }

    @Test
    public void removeTask_numTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test");
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfTasks());
    }

    @Test
    public void removeCompletedTask_numTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test", true);
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfTasks());
    }

    @Test
    public void removeCompletedTask_numCompletedTasksDecremented() {
        TaskList tasks = new TaskList();
        Task t = new Task("test", true);
        tasks.add(t);
        tasks.remove(t);
        assertEquals(0, tasks.getNumOfCompletedTasks());
    }

    @Test
    public void checkDuplicates_success() {
        TaskList tasks = new TaskList();
        Task t = new Task("test");
        tasks.add(t);
        assertTrue(tasks.checkDuplicates("test"));
    }

    @Test
    public void checkScheduleClash_event2BeforeEvent1_false() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2022, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2022, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        assertFalse(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }

    @Test
    public void checkScheduleClash_event2AfterEvent1_false() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2024, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2024, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        assertFalse(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }

    @Test
    public void checkScheduleClash_event2ContainsEvent1_true() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2022, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2024, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        assertTrue(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }

    @Test
    public void checkScheduleClash_event1ContainsEvent2_true() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 1)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 58)));
        assertTrue(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }

    @Test
    public void checkScheduleClash_event2OverlapEvent1Start_true() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2022, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 1)));
        assertTrue(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }

    @Test
    public void checkScheduleClash_event2OverlapEvent1End_true() {
        TaskList tasks = new TaskList();
        Event event1 = new Event("test1",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 0)),
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(23, 59)));
        Event event2 = new Event("test2",
            LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                LocalTime.of(0, 1)),
            LocalDateTime.of(LocalDate.of(2024, Month.JANUARY, 1),
                LocalTime.of(0, 0)));
        assertTrue(tasks.checkScheduleClash(event1.getStartDateTime(),
            event1.getEndDateTime(), event2.getStartDateTime(), event2.getEndDateTime()));
    }
}
