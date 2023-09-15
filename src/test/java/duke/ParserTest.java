package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class ParserTest {
    @Test
    public void parseTest() {
        String[] inputs = {"deadline return book /by 2023-09-01",
            "event project meeting /from 2023-09-01 /to 2023-09-01"};
        try {
            Command[] expecteds = {
                new DeadlineCommand("return book", LocalDate.parse("2023-09-01")),
                new EventCommand("project meeting", LocalDate.parse("2023-09-01"),
                    LocalDate.parse("2023-09-01"))
            };
            for (int i = 0; i < inputs.length; i++) {
                Command c = Parser.parse(inputs[i]);
                assertTrue(expecteds[i].equals(c));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }


    @Test
    void parseTaskTest() {
        String[][] inputs = {{"deadline", "return book", "2023-09-01"},
            {"event", "project meeting", "2023-09-01", "2023-09-01"}};
        Task[] expected = {
            new Deadline("return book", "2023-09-01"),
            new Event("project meeting", "2023-09-01", "2023-09-01")
        };
        for (int i = 0; i < inputs.length; i++) {
            try {
                Task c = Parser.parseTask(inputs[i]);
                assertTrue(expected[i].equals(c));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
