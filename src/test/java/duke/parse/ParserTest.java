package duke.parse;

import duke.parse.command.AddCommand;
import duke.parse.command.EchoCommand;
import duke.parse.command.EmptyCommand;
import duke.parse.command.ExitCommand;
import duke.parse.command.ListCommand;
import duke.parse.command.MarkCommand;
import duke.parse.command.SaveCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private void notifyError() {
        fail("should not throw any error");
    }

    @Test
    public void emptyTest() {
        try {
            assertEquals(new EmptyCommand(), Parser.parse(""));
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void exitTest() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("exit"));
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void listTest() {
        try {
            assertEquals(
                    new ListCommand(false, null, ListCommand.Type.DEFAULT),
                    Parser.parse("list")
            );
            assertEquals(
                    new ListCommand(false, LocalDate.now(), ListCommand.Type.DEFAULT),
                    Parser.parse("list today")
            );
            assertEquals(
                    new ListCommand(true, null, ListCommand.Type.DEFAULT),
                    Parser.parse("list -d")
            );
            assertEquals(
                    new ListCommand(false, null, ListCommand.Type.TODO),
                    Parser.parse("list todo")
            );
            assertEquals(
                    new ListCommand(true, null, ListCommand.Type.TODO),
                    Parser.parse("list todo -d")
            );
            assertEquals(
                    new ListCommand(true, LocalDate.now().plusDays(1), ListCommand.Type.DEADLINE),
                    Parser.parse("list deadline tmr -d")
            );
            assertEquals(
                    new ListCommand(false, LocalDate.of(2023, 9, 5), ListCommand.Type.EVENT),
                    Parser.parse("list event 5/9/2023")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("list event 31/9/2023")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("list event 12-12")
            );
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void markTest() {
        try {
            assertEquals(
                    new MarkCommand(true, 1),
                    Parser.parse("mark 2")
            );
            assertEquals(
                    new MarkCommand(true, 4),
                    Parser.parse("mark 5")
            );
            assertEquals(
                    new MarkCommand(false, 3),
                    Parser.parse("unmark 4")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("mark 0")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("unmark -1")
            );
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void addTest() {
        try {
            assertEquals(
                    new AddCommand(new ToDo("do sth")),
                    Parser.parse("todo do sth")
            );
            assertEquals(
                    new AddCommand(new Deadline(
                            "do sth",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0))
                    )),
                    Parser.parse("deadline do sth /by today 10pm")
            );
            assertEquals(
                    new AddCommand(new Event(
                            "attend sth",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)),
                            LocalDateTime.of(2023, 9, 5, 21, 2)
                    )),
                    Parser.parse("event attend sth /from today 10am /to 5/9/2023 9:02pm")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("event /from today 10am /to tmr 9pm")
            );
            assertThrows(
                    Parser.ParseError.class,
                    () -> Parser.parse("event do sth /from today 19:70 /to tmr 10am")
            );
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void saveTest() {
        try {
            assertEquals(
                    new SaveCommand(),
                    Parser.parse("save")
            );
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }

    @Test
    public void echoTest() {
        try {
            assertEquals(
                    new EchoCommand("quack"),
                    Parser.parse("quack")
            );
            assertEquals(
                    new EchoCommand("fevwnqbo"),
                    Parser.parse("fevwnqbo")
            );
        } catch (Parser.ParseError e) {
            notifyError();
        }
    }
}
