package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    private static final Duke DUKE = new Duke("data/testDuke.txt");

    @Test
    public void getResponse_bye_expectedString() {
        String response = DUKE.getResponse("bye");
        assertEquals("Bye. Hope to see you again soon!", response);
    }

    @Test
    public void getResponse_list_expectedString() {
        String response = DUKE.getResponse("list");
        assertEquals("Here are the tasks in your list:\n"
                        + "1.[T][X][LOW] read book\n"
                        + "2.[D][ ][MEDIUM] return book (by: 06 Jun 2023 - 16:00)\n"
                        + "3.[E][ ][HIGH] project meeting (from: 06 Aug 2023 - 14:00 to: 06 Aug 2023 - 16:00)",
                response);
    }

    @Test
    public void getResponse_markTaskValidIndex_expectedString() {
        String response = DUKE.getResponse("mark 3");
        assertEquals("Nice! I've marked this task as done:\n"
                        + "[E][X][HIGH] project meeting (from: 06 Aug 2023 - 14:00 to: 06 Aug 2023 - 16:00)",
                response);
    }

    @Test
    public void getResponse_markTaskInvalidIndex_exceptionMessage() {
        String response = DUKE.getResponse("mark 7");
        assertEquals("OOPS!!! Index of task to mark is out of bounds", response);
    }

    @Test
    public void getResponse_unmarkTaskValidIndex_expectedString() {
        String response = DUKE.getResponse("unmark 3");
        assertEquals("OK, I've marked this task as not done yet:\n"
                        + "[E][ ][HIGH] project meeting (from: 06 Aug 2023 - 14:00 to: 06 Aug 2023 - 16:00)",
                response);
    }

    @Test
    public void getResponse_unmarkTaskInvalidIndex_exceptionMessage() {
        String response = DUKE.getResponse("unmark 7");
        assertEquals("OOPS!!! Index of task to unmark is out of bounds", response);
    }

    @Test
    public void getResponse_deleteTaskInvalidIndex_expectedString() {
        String response = DUKE.getResponse("delete 4");
        assertEquals("OOPS!!! Index of task to delete is out of bounds", response);
    }

    @Test
    public void getResponse_findTaskHasInstance_expectedString() {
        String response = DUKE.getResponse("find book");
        assertEquals("Here are the matching tasks in your list:\n"
                        + "1.[T][X][LOW] read book\n"
                        + "2.[D][ ][MEDIUM] return book (by: 06 Jun 2023 - 16:00)",
                response);
    }

    @Test
    public void getResponse_findTaskHasNoInstance_expectedString() {
        String response = DUKE.getResponse("find dance");
        assertEquals("Here are the matching tasks in your list:\n", response);
    }

    @Test
    public void getResponse_changePriorityValidIndex_expectedString() {
        String response = DUKE.getResponse("change priority 2 low");
        String reverseAction = DUKE.getResponse("change priority 2 medium");
        assertEquals("Noted. I've changed the priority of this task:\n"
                        + "[D][ ][LOW] return book (by: 06 Jun 2023 - 16:00)",
                response);
    }

    @Test
    public void getResponse_changePriorityInvalidIndex_exceptionMessage() {
        String response = DUKE.getResponse("change priority 5 low");
        assertEquals("OOPS!!! Index of task to change priority is out of bounds", response);
    }

    @Test
    public void getResponse_changePriorityInvalidPriority_exceptionMessage() {
        String response = DUKE.getResponse("change priority 2 unknown");
        assertEquals("", response);
    }
}
