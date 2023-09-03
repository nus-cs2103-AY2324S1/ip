package robert.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    private final LocalDate sampleDateOne = LocalDate.parse("2022-05-17");
    private final LocalDate sampleDateTwo = LocalDate.parse("2023-11-02");
    private final LocalDate sampleDateThree = LocalDate.parse("2021-07-29");

    @Test
    public void dateRetrieval() {
        try {
            assertEquals(this.sampleDateOne.toString(),
                    new Event("EVENTTEST",
                            this.sampleDateOne, this.sampleDateTwo).getFromDate().toString());

            assertEquals(this.sampleDateTwo.toString(),
                    new Event("EVENTTEST",
                            this.sampleDateOne, this.sampleDateTwo).getToDate().toString());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void happeningOnChecker() {
        try {
            assertTrue(new Event("EVENTTEST",
                    this.sampleDateOne, this.sampleDateTwo).isHappeningOn(this.sampleDateOne));

            assertFalse(new Event("EVENTTEST",
                    this.sampleDateOne, this.sampleDateTwo).isHappeningOn(this.sampleDateThree));
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void eventInitialise_toDateBeforeFromDate_exceptionThrown() {
        try {
            new Event("EVENTTEST", this.sampleDateTwo, this.sampleDateOne);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The end date of the event is before the start date.\n"
                    + "Please set the dates such that the start date is before/on the end date.",
                    e.toString());
        }
    }
}
