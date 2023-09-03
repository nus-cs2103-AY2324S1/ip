package robert.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    private final LocalDate sampleDateOne = LocalDate.parse("2023-05-17");
    private final LocalDate sampleDateTwo = LocalDate.parse("2022-11-02");

    @Test
    public void dateRetrieval() {
        assertEquals(this.sampleDateOne.toString(),
                new Deadline("DEADLINETEST",
                        this.sampleDateOne).getByDate().toString());
    }

    @Test
    public void dueOnChecker() {
        assertTrue(new Deadline("DEADLINETEST",
                this.sampleDateOne).isDueOn(this.sampleDateOne));

        assertFalse(new Deadline("DEADLINETEST",
                this.sampleDateOne).isDueOn(this.sampleDateTwo));
    }
}
