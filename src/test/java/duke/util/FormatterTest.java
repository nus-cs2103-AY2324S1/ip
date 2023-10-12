package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FormatterTest {

    @Test
    public void stringifyList_stringList_enumeratedList() {
        assertEquals(Formatter.stringifyList(List.of("first", "second", "third")), "1. first\n2. second\n3. third");
    }

    @Test
    public void stringifyList_intList_enumeratedList() {
        assertEquals(Formatter.stringifyList(List.of(1, 2, 3)), "1. 1\n2. 2\n3. 3");
    }

    @Test
    public void stringifyList_emptyList_emptyString() {
        assertEquals(Formatter.stringifyList(List.of()), "");
    }

}
