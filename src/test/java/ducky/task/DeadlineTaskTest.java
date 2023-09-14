package ducky.task;

import com.ducky.task.DeadlineTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    DeadlineTask deadlineTask1 = new DeadlineTask(
            "test1",
            LocalDate.parse("2019-01-01")
    );
    DeadlineTask deadlineTask2 = new DeadlineTask(
            "test2",
            LocalDate.parse("2023-08-30")
    );

    @BeforeEach
    public void setDeadlineTask2ToCompleted() {
        deadlineTask2.setComplete();
    }

    @Test
    public void deadlineTask_toString() {
        assertEquals(
                deadlineTask1.toString(),
                "[D][ ] test1 (by: Jan 01 2019)"
                );
        assertEquals(
                deadlineTask2.toString(),
                "[D][X] test2 (by: Aug 30 2023)"
        );
    }

    @Test
    public void deadlineTask_getSaveFormat() {
        assertEquals(
                deadlineTask1.getSaveFormat(),
                "D | 0 | test1 | 2019-01-01"
        );
        assertEquals(
                deadlineTask2.getSaveFormat(),
                "D | 1 | test2 | 2023-08-30"
        );
    }
}
