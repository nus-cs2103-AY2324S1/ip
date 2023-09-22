package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void readFromFile_correctlyFormatted_allTasksCorrespond() {
        TestStorage sTest1 = new TestStorage("T/%-%/0/%-%/1, 2, 3/%-%//;/D/%-%/0/%-%/return book/%-%/2000-01-01/%-%//;/E/%-%/0/%-%/fair/%-%/2000-01-01/%-%/2000-01-02/%-%//;/");

        assertEquals(new ToDo("1, 2, 3").toString(), sTest1.get(0).toString());
        assertEquals(new Deadline("return book", "2000-01-01").toString(), sTest1.get(1).toString());
        assertEquals(new Event("fair", "2000-01-01", "2000-01-02").toString(), sTest1.get(2).toString());
    }

    @Test
    public void readFromFile_correctlyFormatted_correctMarkingAndUnmarking() {
        TestStorage sTest2 = new TestStorage("T/%-%/0/%-%/1, 2, 3/%-%//;/");

        sTest2.get(0).markAsDone();
        assertTrue(sTest2.get(0).isDone);

        sTest2.get(0).markAsUndone();
        assertFalse(sTest2.get(0).isDone);
    }

    @Test
    public void readFromFile_improperGetCall_indexOutOfBoundsExceptionThrown() {
        TestStorage sTest3 = new TestStorage("T/%-%/0/%-%/1, 2, 3/%-%//;/");
        try {
            sTest3.get(1);
        } catch (Exception e) {
            assertInstanceOf(IndexOutOfBoundsException.class, e);
        }
    }

    @Test
    public void readFromFile_correctlyFormatted_addingAndremoving() {
        TestStorage sTest4 = new TestStorage("T/%-%/0/%-%/1, 2, 3/%-%//;/");

        sTest4.appendTask(new ToDo("4, 5, 6"));
        assertEquals("1. [T] [ ] 1, 2, 3 \n2. [T] [ ] 4, 5, 6 \n", sTest4.list());

        sTest4.delete(0);
        assertEquals("1. [T] [ ] 4, 5, 6 \n", sTest4.list());

        try {
            sTest4.get(1);
        } catch (Exception e) {
            assertInstanceOf(IndexOutOfBoundsException.class, e);
        }
    }
}
