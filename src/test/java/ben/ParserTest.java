package ben;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void invalidCommandTest(){
        try {
            Parser parser = new Parser(new TaskList());
            parser.parse("mark 1");
        } catch (InvalidCommandException e) {
            Assertions.assertEquals("Please input a valid task number", e.getMessage());
        }
    }

    @Test
    public void validCommandTest(){
        try {
            Parser parser = new Parser(new TaskList());
            parser.parse("todo send email");
        } catch (InvalidCommandException e) {
            Assertions.fail();
        }
    }
}
