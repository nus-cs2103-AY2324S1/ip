package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.*;

public class TasklistTest {
    @Test
    public void markTest() {
        Tasklist list = new Tasklist();
        try {
            list.addtolist("todo sleep");
            list.mark(1);
        } catch (DukeException e) {
            // do nothing
        }
        assertEquals( "1. [T][X] sleep\n", list.toString(),"'mark' should mark task as done");
    }

    @Test
    public void deleteTest() {
        Tasklist list = new Tasklist();
        try {
            list.addtolist("todo sleep");
            list.addtolist("todo go home");
            list.addtolist("todo codeforces");
            list.delete(2);
        } catch (DukeException e) {
            // do nothing
        }
        assertEquals("1. [T][ ] sleep\n2. [T][ ] codeforces\n", list.toString(), "'delete' should remove the second task");
    }
}