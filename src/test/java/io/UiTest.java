package io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;




public class UiTest {

    @Test
    public void displayTask_deadline_output() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        String name = "hello ";
        Deadline input = new Deadline(name, date);
        Ui ui = new Ui();
        String output = ui.displayTask(input);
        assertEquals("[D][ ] hello (2021-01-01)", output);
    }

    @Test
    public void displayTask_event_output() {
        String name = "hello ";
        LocalDateTime start = LocalDateTime.of(2012,1,1,1,1);
        LocalDateTime end = LocalDateTime.of(2012,1,1,2,2);
        Ui ui = new Ui();
        Event input = new Event(name, start, end);
        String output = ui.displayTask(input);
        assertEquals("[E][ ] hello (2012-01-01T01:01 to 2012-01-01T02:02)", output);
    }

    @Test
    public void displayTask_todoobj_output() {
        String name = "hello ";
        Ui ui = new Ui();
        Todo input = new Todo(name);
        String output = ui.displayTask(input);
        assertEquals("[T][ ] hello ", output);
    }

}
