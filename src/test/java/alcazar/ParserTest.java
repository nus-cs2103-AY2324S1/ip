package alcazar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    void textAfterTest() {
        try {
            assertEquals("birthday /from 2pm /to 6pm", new Parser().textAfter("event birthday /from 2pm /to 6pm"));
        } catch (InvalidArgumentException e) {
            System.out.println("Exception thrown");
        }
    }

    @Test
    void extractDeadlineTest() {
        String s[] = {"submit assignment","Oct 4th 1800"};
        assertEquals(s[0], new Parser().extractDeadline("submit assignment /by Oct 4th 1800")[0]);
        assertEquals(s[1], new Parser().extractDeadline("submit assignment /by Oct 4th 1800")[1]);
    }

}
