package duke;

import duke.task.PriorityComparator;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.task.TodoTask;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityComparatorTest {
        @Test
        public void priorityComparatorTest() {
            TodoTask highPriority = new TodoTask("High priority task", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"),false, TaskPriority.HIGH);
            TodoTask mediumPriority = new TodoTask("Medium priority task", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.MEDIUM);
            TodoTask lowPriority = new TodoTask("Low priority task", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"),false, TaskPriority.LOW);

            List<Task> tasks = new ArrayList<>();
            tasks.add(mediumPriority);
            tasks.add(lowPriority);
            tasks.add(highPriority);

            Collections.sort(tasks, new PriorityComparator());

            assertEquals(highPriority, tasks.get(0));
            assertEquals(mediumPriority, tasks.get(1));
            assertEquals(lowPriority, tasks.get(2));
        }
}
