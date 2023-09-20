package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.MissingTitleException;


public class ParserTest {

    @Test
    public void obtainTitle_success() {
        try {
            assertEquals("return books", Parser.obtainTitle("todo return books", Commands.TODO));
            assertEquals("write essay",
                    Parser.obtainTitle("deadline write essay /by 2024-01-01", Commands.DEADLINE));
            assertEquals("hackathon",
                    Parser.obtainTitle("event hackathon /from 2024-01-01 /to 2024-01-02", Commands.EVENT));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void obtainTitle_exceptionThrown() {
        try {
            assertEquals("hackathon",
                    Parser.obtainTitle("event /from 2024-01-01 /to 2024-01-02", Commands.EVENT));
            fail();
        } catch (MissingTitleException e) {
            assertEquals("Title of the task is missing! Please give your task a name :)", e.getMessage());
        } catch (Exception e) {
            fail("here");
        }
    }
}
