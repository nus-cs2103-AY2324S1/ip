package bot.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InputParserTest {

    /**
     * Test to see if only the first space is split even when multiple spaces are present in string
     */
    @Test
    public void getSplitAtSpace_multipleSpaces_success() {
        String[] expectedOutput = {"dinner", "table is huge."};
        String[] actualOutput = InputParser.getSplitAtSpace("dinner table is huge.");
        assertArrayEquals(expectedOutput, actualOutput);
    }

    /**
     * Test to see if the same string is outputted when no ' /by ' is present in string
     */
    @Test
    public void getSplitAtBy_missingBy_success() {
        String[] expectedOutput = {"dinner table is huge."};
        String[] actualOutput = InputParser.getSplitAtBy("dinner table is huge.");
        assertArrayEquals(expectedOutput, actualOutput);
    }

}
