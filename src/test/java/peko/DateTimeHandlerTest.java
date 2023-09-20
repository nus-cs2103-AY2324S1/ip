package peko;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DateTimeHandlerTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void testValidInput() {
        String s = "19/11/2001 1023";
        String target = "NOVEMBER 19 2001 10:23";

        assertEquals(target, (new DateTimeHandler(s)).stringDisplay());

    }
}
