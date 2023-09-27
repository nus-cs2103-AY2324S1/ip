package rat.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * This class tests the RatStorage class.
 * @author Keagan
 */
public class RatStorageTest {

    /**
     * The RatStorage object used for testing.
     * A sample file is used for testing.
     */
    protected RatStorage rsTest = new RatStorage("testdata/rsTest.txt");

    /**
     * Tests the overwriteFile method.
     * The file is overwritten with an empty String, and the file is checked to be empty.
     */
    @Test
    public void testEmpty_isEmpty() {
        this.rsTest.overwriteFile("");
        assertTrue(rsTest.isFileEmpty());
    }

    /**
     * Tests the overwriteFile method.
     * The file is overwritten with a String, and the file is checked to contain the matching String.
     */
    @Test
    public void testReadWriteFile() {
        this.rsTest.overwriteFile("test");
        assertEquals("test", this.rsTest.readFile().trim());
    }

    /**
     * Tests the overwriteFile method.
     * The file is overwritten with a String containing multiple lines, and the file is checked to contain
     * the matching String.
     */
    @Test
    public void testReadWriteFile_multipleLines() {
        this.rsTest.overwriteFile("test\ntest2");
        assertEquals("test\ntest2", this.rsTest.readFile().trim());
    }

    /**
     * Tests the RatStorage class using a bad file path, and checks if an exception is thrown.
     */
    @Test
    public void testBadFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> {
            new RatStorage("badFilePath");
        });
    }

}
