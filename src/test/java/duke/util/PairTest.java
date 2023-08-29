package duke.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairTest {

    @Test
    public void getKey_validPair_correctKeyReturned() {
        Pair pair = new Pair("name", "duke");
        assertEquals("name", pair.getKey());
    }

    @Test
    public void getValue_validPair_correctValueReturned() {
        Pair pair = new Pair("name", "duke");
        assertEquals("duke", pair.getValue());
    }

}
