package adam;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import adam.exception.AdamException;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_randomInput_exceptionThrown() {
        try{
            Parser.parse("bla");
            fail();
        } catch (AdamException e) {
            assertEquals("OOPS!!! I don't know what this means",e.getInfo());
        }
    }
    @Test
    public void parse_noInput_exceptionThrown() {
        try{
            Parser.parse("");
            fail();
        } catch (AdamException e) {
            assertEquals("OOPS!!! I don't know what this means",e.getInfo());
        }
    }
}
