package bot.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InputParserTest {

    @Test
    public void getSplitAtSpace_multipleSpaces_success() {
        String[] expectedOutput = {"dinner", "table is huge."};
        String[] actualOutput = InputParser.getSplitAtSpace("dinner table is huge.");
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getSplitAtBy_missingBy_success() {
        String[] expectedOutput = {"dinner table is huge."};
        String[] actualOutput = InputParser.getSplitAtBy("dinner table is huge.");
        assertArrayEquals(expectedOutput, actualOutput);
    }

}
