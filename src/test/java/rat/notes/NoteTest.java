package rat.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void testNote_create() {
        Note noteTest = new Note("test");
        assertEquals(noteTest.toString(), "[Note] test");
    }

    @Test
    public void testNote_formatForFile() {
        Note noteTest = new Note("test");
        assertEquals("N/ test", noteTest.formatForFile());
    }
}
