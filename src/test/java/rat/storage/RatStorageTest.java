package rat.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

public class RatStorageTest {

    protected RatStorage rsTest = new RatStorage("testdata/rsTest.txt");

    @Test
    public void testEmpty_isEmpty() {
        this.rsTest.overwriteFile("");
        assertTrue(rsTest.isFileEmpty());
    }

    @Test
    public void testReadWriteFile() {
        this.rsTest.overwriteFile("test");
        assertEquals("test", this.rsTest.readFile().trim());
    }

    @Test
    public void testReadWriteFile_multipleLines() {
        this.rsTest.overwriteFile("test\ntest2");
        assertEquals("test\ntest2", this.rsTest.readFile().trim());
    }

    @Test
    public void testBadFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> {
            new RatStorage("badFilePath");
        });
    }

}
