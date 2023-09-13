package seedu.duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.assets.tasks.Todo;
import duke.assets.tasks.Event;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals("T | 0 | dance", new Todo("dance").saveToTextFormat());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals("E | 0 | sleep | 2015-05-05 1600 - 2016-05-05 1600",
                new Event("sleep", "2015-05-05 1600", "2016-05-05 1600").saveToTextFormat());
    }
}