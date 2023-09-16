package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTDTest() {
        assertEquals("Watch Youtube", new Parser("").getTaskDescription(new String[]{"todo", "Watch", "Youtube"}));
    }

    @Test
    public void parserSearcherTest() {
        assertEquals("", new Parser("").searcher(new String[]{"hello", "world", "-"}, "/to"));
    }
}
