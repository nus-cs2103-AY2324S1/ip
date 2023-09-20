package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class EventTest {
    @Test
    public void eventTaskType_shouldBeE() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        String expected = "E";
        String actual = task.getTaskType();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isMarkedForInitialisedEvent_shouldBeFalse() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        Assertions.assertEquals("[ ] ", task.showMarked());
    }

    @Test
    public void isMarkedForSetMarked_shouldBeTrue() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        task.setMarked();
        Assertions.assertEquals("[X] ", task.showMarked());
    }
    @Test
    public void getTime_shouldReturnFormattedString() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        String expected = "2023-08-05 12:11 to 2023-08-05 14:11";
        String actual = task.getTime();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void saveFormat_shouldBeEventSaveFormat() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        String expected = "E / [ ] / test / 2023-08-05 12:11 / 2023-08-05 14:11";
        String actual = task.saveFormat();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void toString_shouldBeEventToStringFormat() throws KoraException {
        Task task = new Event("test", "2023-08-05 12:11", "2023-08-05 14:11");
        String expected = "[E][ ] test (from: Sat, Aug 5 2023 12:11 to: Sat, Aug 5 2023 14:11)";
        String actual = task.toString();
        Assertions.assertEquals(expected, actual);
    }
}
