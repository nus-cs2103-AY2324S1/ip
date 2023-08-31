package duke.utilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void test1() {
        Input input = parser.parse("todo eat");
        String command = input.getCommand();
        int length = input.getLength();
        String fullInput = input.getFullInput();
        assertEquals(command, "todo");
        assertEquals(length, 2);
        assertEquals(fullInput, "todo eat");
    }

    @Test
    public void test2() {
        Input input = parser.parse("event camp /from 2023-03-05 /to 2023-03-06");
        String command = input.getCommand();
        int length = input.getLength();
        String fullInput = input.getFullInput();
        assertEquals(command, "event");
        assertEquals(length, 6);
        assertEquals(fullInput, "event camp /from 2023-03-05 /to 2023-03-06");
    }

    @Test
    public void test3() {
        Input input = parser.parse("hello world, nice to meet you all");
        String command = input.getCommand();
        int length = input.getLength();
        String fullInput = input.getFullInput();
        assertEquals(command, "hello");
        assertEquals(length, 7);
        assertEquals(fullInput, "hello world, nice to meet you all");
    }

    @Test
    public void test4() {
        String formattedDate = parser.formatDate("2023-09-03");
        assertEquals(formattedDate, "Sep 03 2023");
    }

    @Test
    public void test5() {
        String formattedDate = parser.formatDate("August 23, 2023");
        assertEquals(formattedDate, "Invalid date");
    }
}
