package jeeves.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskListDataAsString_emptyList_returnEmptyString() {
        TaskList testTaskList = new TaskList();
        assertEquals("", testTaskList.getTaskListDataAsString());
    }

    @Test
    public void getTaskListDataAsString_allNullList_returnEmptyString() {
        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(null);
        testArrayList.add(null);
        testArrayList.add(null);
        TaskList testTaskList = new TaskList(testArrayList);
        assertEquals("", testTaskList.getTaskListDataAsString());
    }

    @Test
    public void getTaskListDataAsString_allTaskData_returnCorrectString() {
        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Todo("some todo desc"));
        testArrayList.add(new Deadline("some deadline desc", LocalDate.parse("2023-09-03")));
        testArrayList.add(new Event("some event desc", "some from date", "some to date"));
        TaskList testTaskList = new TaskList(testArrayList);
        System.out.println(testTaskList.getTaskListDataAsString());
        assertEquals("T|0|some todo desc\n"
                + "D|0|some deadline desc|2023-09-03\n"
                + "E|0|some event desc|some from date|some to date\n"
                , testTaskList.getTaskListDataAsString());
    }
}
