package dot.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dot.errors.DotException;
import dot.storage.Storage;

public class TaskListTest {
    @Test
    public void getDisplayForTasksFallingOnDate_boundaryAndNormalDates_success() {
        try {
            Event testEvent1 = new Event("Test Event 1", "24/05/2021 0000", "25/06/2021 0000");
            Event testEvent2 = new Event("Test Event 2", "24/05/2021 0000", "25/06/2021 2359");
            Event testEvent3 = new Event("Test Event 3", "24/05/2021 0000", "25/06/2021 1235");

            Deadline deadline1 = new Deadline("Test Deadline 1", "1/6/2021 0000");
            Deadline deadline2 = new Deadline("Test Deadline 2", "01/06/2021 2359");
            Deadline deadline3 = new Deadline("Test Deadline 3", "timeDescription");

            Task[] tasks = new Task[] {testEvent1, testEvent2, testEvent3, deadline1, deadline2, deadline3};
            Storage storage = new Storage("./data/junit-test-data.txt");
            TaskList testTaskList = TaskList.getTaskListFromArrayList(100,
                    new ArrayList<>(Arrays.asList(tasks)), storage);

            testTaskList.saveTaskListToStorage();

            LocalDateTime startOfDay = LocalDateTime.of(2021, 6, 1, 0, 0);

            ArrayList<String> stringArrayList = testTaskList.getDisplayForTasksFallingOnDate(startOfDay);
            stringArrayList.remove(0);
            String[] actualOutput = stringArrayList.toArray(new String[] {});
            String[] expectedOutput = new String[] {
                    testEvent1.toString(),
                    testEvent2.toString(),
                    testEvent3.toString(),
                    deadline1.toString(),
                    deadline2.toString()
            };
            Assertions.assertArrayEquals(expectedOutput, actualOutput);
        } catch (DotException e) {
            e.handleError();
        }
    }
}
