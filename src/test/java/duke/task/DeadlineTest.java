package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class DeadlineTest {
    @Test
    public void deadlineTaskType_shouldBeD() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        String expected = "D";
        String actual = task.getTaskType();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isMarkedForInitialisedDeadline_shouldBeFalse() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        Assertions.assertEquals("[ ] ", task.showMarked());
    }

    @Test
    public void isMarkedForSetMarked_shouldBeTrue() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        task.setMarked();
        Assertions.assertEquals("[X] ", task.showMarked());
    }
    @Test
    public void getTime_shouldReturnFormattedString() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        String expected = "2023-08-05 12:11";
        String actual = task.getTime();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void saveFormat_shouldBeDeadlineSaveFormat() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        String expected = "D / [ ] / test / 2023-08-05 12:11";
        String actual = task.saveFormat();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toString_shouldBeDeadlineToStringFormat() throws KoraException {
        Task task = new Deadline("test", "2023-08-05 12:11");
        String expected = "[D][ ] test (by: Sat, Aug 5 2023 12:11)";
        String actual = task.toString();
        Assertions.assertEquals(expected, actual);
    }
}
