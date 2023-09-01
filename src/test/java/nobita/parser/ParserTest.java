package nobita.parser;

import nobita.exception.NobitaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void nonIntegerTest() {
        try {
            NobitaException exception = assertThrows(NobitaException.class, () -> {
                Parser.parse("mark x");
            });

            String expectedMessage = "Only numbers are allow";
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Something went wrong with the test");
        }
    }

    @Test
    public void parameterTest() {
        try {
            NobitaException exception = assertThrows(NobitaException.class, () -> {
                Parser.parse("deadline");
            });

            String expectedMessage = "The description of a deadline cannot be empty.\n"
                    + "Please specify a description.";
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Something went wrong with the test");
        }
    }
}
