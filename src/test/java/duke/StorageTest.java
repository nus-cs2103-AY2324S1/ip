package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {

    @Test
    public void readFromFile_correctlyFormatted_allTasksCorrespond() {
        TestStorage sTest = new TestStorage("T/%-%/0/%-%/1, 2, 3/%-%//;/D/%-%/0/%-%/return book/%-%/2000-01-01/%-%//;/E/%-%/0/%-%/fair/%-%/2000-01-01/%-%/2000-01-02/%-%//;/");

        assertEquals(sTest.get(0), new ToDo("1, 2, 3").toString());
        assertEquals(sTest.get(1), new Deadline("read", "2000-01-01").toString());
    }
}
