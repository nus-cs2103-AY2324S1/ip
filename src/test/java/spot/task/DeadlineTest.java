package spot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void fallsOn_validDate_success() {
        assertEquals(true, new Deadline("wah",
                LocalDate.of(2023, 8, 28))
                .fallsOn(LocalDate.of(2023, 8, 28)));
        assertEquals(false, new Deadline("wah",
                LocalDate.of(2023, 8, 28))
                .fallsOn(LocalDate.of(2023, 8, 29)));
    }

}
