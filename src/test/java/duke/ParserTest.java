package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseAndRespond_wrongInput_exceptionThrown() {
        Parser p = new Parser("abc");
        try {
            p.parseAndRespond();
        } catch (DukeException e) {
            assertEquals("Huhhhhhhh??? (o_O) ? Please use one of the command words: "
                            + "todo, event, deadline, list, mark, unmark, delete, bye",
                        e.getMessage());
        }
    }

    @Test
    public void parseAndRespond_noDeadline_exceptionThrown() {
        Parser p = new Parser("deadline something");
        try {
            p.parseAndRespond();
        } catch (DukeException e) {
            assertEquals("Please provide a deadline! (⋟﹏⋞)", e.getMessage());
        }
    }

    @Test
    public void parseAndRespond_noStartTime_exceptionThrown() {
        Parser p = new Parser("event testing");
        try {
            p.parseAndRespond();
        } catch (DukeException e) {
            assertEquals("Please provide a start time! (⋟﹏⋞)", e.getMessage());
        }
    }

    @Test
    public void parseAndRespond_noEndTime_exceptionThrown() {
        Parser p = new Parser("event testing /from 2pm");
        try {
            p.parseAndRespond();
        } catch (DukeException e) {
            assertEquals("Please provide an end time! (⋟﹏⋞)", e.getMessage());
        }
    }

    @Test
    public void parseAndRespond_noTaskIndicated_exceptionThrown() {
        Parser p = new Parser("mark");
        try {
            p.parseAndRespond();
        } catch (DukeException e) {
            assertEquals("Please indicate which task to mark!", e.getMessage());
        }
    }

}