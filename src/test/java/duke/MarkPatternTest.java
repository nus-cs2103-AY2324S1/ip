package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkPatternTest {

    @Test
    public void testMarkPatternWithValidInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.mark("mark 5");
        assertEquals(4, result, "Mark pattern test failed: incorrect result");
    }

    @Test
    public void testMarkPatternWithDifferentInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.mark("mark 10");
        assertEquals(9, result, "Mark pattern test failed: incorrect result");
    }

    @Test
    public void testMarkPatternWithNoNumber() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.mark("mark");
        assertEquals(-1, result, "Mark pattern test failed: incorrect result");
    }

    @Test
    public void testMarkPatternWithSpaces() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.mark("  mark    7 ");
        assertEquals(6, result, "Mark pattern test failed: incorrect result");
    }

    @Test
    public void testUnmarkPatternWithValidInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.unmark("unmark 3");
        assertEquals(2, result, "Unmark pattern test failed: incorrect result");
    }

    @Test
    public void testUnmarkPatternWithDifferentInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.unmark("unmark 8");
        assertEquals(7, result, "Unmark pattern test failed: incorrect result");
    }

    @Test
    public void testUnmarkPatternWithNoNumber() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.unmark("unmark");
        assertEquals(-1, result, "Unmark pattern test failed: incorrect result");
    }

    @Test
    public void testUnmarkPatternWithSpaces() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.unmark("  unmark   5 ");
        assertEquals(4, result, "Unmark pattern test failed: incorrect result");
    }

    @Test
    public void testDeletePatternWithValidInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.del("delete 2");
        assertEquals(1, result, "Delete pattern test failed: incorrect result");
    }

    @Test
    public void testDeletePatternWithDifferentInput() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.del("delete 7");
        assertEquals(6, result, "Delete pattern test failed: incorrect result");
    }

    @Test
    public void testDeletePatternWithNoNumber() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.del("delete");
        assertEquals(-1, result, "Delete pattern test failed: incorrect result");
    }

    @Test
    public void testDeletePatternWithSpaces() {
        MarkPattern markPattern = new MarkPattern();
        int result = markPattern.del("  delete   3 ");
        assertEquals(2, result, "Delete pattern test failed: incorrect result");
    }
}


