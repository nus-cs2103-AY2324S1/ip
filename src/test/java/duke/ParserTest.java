package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.helper.DukeException;
import duke.helper.Parser;

public class ParserTest {
    @Test
    public void todoTest() throws DukeException {
        String input = "todo task 1";
        String[] result = Parser.parseTest(input);
        String command = result[0];
        String todo = result[1];
        assertEquals(command, "TODO");
        assertEquals(todo, "[T - D][In Progress] task 1");
    }

    @Test
    public void deadlineTest() throws DukeException {
        String input = "deadline task 2 /by 2023-01-01";
        String[] result = Parser.parseTest(input);
        String command = result[0];
        String deadline = result[1];
        assertEquals(command, "DEADLINE");
        assertEquals(deadline, "[DDL][In Progress] task 2 (by: Jan. 1 2023)");
    }

    @Test
    public void eventTest() throws DukeException {
        String input = "event task 3 /from 2023-01-01 /to 2023-01-02";
        String[] result = Parser.parseTest(input);
        String command = result[0];
        String event = result[1];
        assertEquals(command, "EVENT");
        assertEquals(event, "[EVT][In Progress] task 3 (from: Jan. 1 2023 to: Jan. 2 2023)");
    }
}
