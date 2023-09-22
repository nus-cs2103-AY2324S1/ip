package duke.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class StorageTest {
    @Test
    public void invalidInputInCheckTask_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    Storage storage = new Storage("./data/test");
                    storage.checkTask(new String[]{"A", "[ ]", "test"});
                });
        String expected = "Omo! Check if the task is valid!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
}
