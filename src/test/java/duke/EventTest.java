package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputExpression;
import duke.parser.Parser;
import duke.ronaldo.RonaldoSaysDo;
import duke.ronaldo.TaskType;
import duke.tasks.Event;

public class EventTest {
    @Test
    public void event_toString_success() {
        Event event = new Event("CS2103T iP", "2022-12-03" , "2022-12-14" , "09:00", "08:00", TaskType.EVENT);
        assertEquals("[E][ ] CS2103T iP(from: Dec 03 2022 09:00 to: Dec 14 2022 08:00)", event.toString());
    }
    @Test
    public void event_setMarkAgain_exception() {
        try {
            Event event = new Event("CS2103T iP", "2022-12-03" , "2022-12-14" , "09:00", "08:00", TaskType.EVENT);
            event.setMarked();
            event.setMarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as done!\n", e.getMessage());
        }
    }
    @Test
    public void event_setUnMarkAgain_exception() {
        try {
            Event event = new Event("CS2103T iP", "2022-12-03" , "2022-12-14" , "09:00", "08:00", TaskType.EVENT);
            event.setUnmarked();
            event.setUnmarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as not done!\n", e.getMessage());
        }
    }
    @Test
    public void event_missingDescription_exception() {
        try {
            String input = "event /from 2023-10-23 09:00 /to 2023-10-24 09:00";
            RonaldoSaysDo ronaldoSaysDo = new RonaldoSaysDo();
            ronaldoSaysDo.handleEventTask(input);
        } catch (DukeException e) {
            assertEquals("SUI, No description specified la dei!! How to do work when no work is said!! "
                    +
                    "Enter again!\n", e.getMessage());
        }
    }
    @Test
    public void event_hasLeadingSpace_exception() {
        try {
            String input = "  event CS2103T tP/from 2023-10-23 09:00 /to 2023-10-24 09:00";
            Parser parser = new Parser();
            parser.handleUserInput(input);
        } catch (DukeException | InvalidInputExpression e) {
            assertEquals("SUI, input has leading spaces", e.getMessage());
        }
    }
}
