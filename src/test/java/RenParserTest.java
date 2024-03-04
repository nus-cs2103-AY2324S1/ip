package ren.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenParserTest {
    //write unit tests for RenParser's methods here

    @Test
    public void parse() {
        assertEquals("[T][] read book  added", new ren.RenParser().parseInputString("todo read book", new ren.task.TaskList()));
    }

}
