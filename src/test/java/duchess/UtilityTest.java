package duchess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UtilityTest {
    @Test
    public void matchesRegexTest() {
        assertTrue(Utility.matchesRegex("abc", "^a"));
        assertTrue(Utility.matchesRegex("abc", "c$"));
        assertTrue(Utility.matchesRegex("abc", "[0-9A-Za-z]+"));

        assertFalse(Utility.matchesRegex("abc", "^a$"));
    }
}
