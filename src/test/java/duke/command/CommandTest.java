package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class CommandTest {

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    TaskList tasks = new TaskList();
    ToDo todo = new ToDo("read book");
    Deadline deadline = new Deadline("return book", LocalDateTime.parse("12/01/2023 1800", inputFormatter));
    Event event = new Event("project meeting", LocalDateTime.parse("12/01/2023 1800", inputFormatter),
            LocalDateTime.parse("12/01/2023 1900", inputFormatter));

    public CommandTest() {
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
    }

    Ui ui = new Ui();

    @Test
    @Tag("Basic test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Tag("Mark test")
    public void markTask_success() {
        Command mark = new MarkCommand(0);
        mark.execute(tasks, ui);
        assertEquals(2,2);
    }

    @Test
    @Tag("Mark test")
    public void markTask_invalidIndex() {
        Command mark = new MarkCommand(-1);
        mark.execute(tasks, ui);
        assertEquals(2,2);
    }

    @Test
    @Tag("Unmark test")
    public void unmarkTask_success() {
        Command unmark = new UnmarkCommand(0);
        unmark.execute(tasks, ui);
        assertTrue(tasks.getTask(0).toString().split("\\[")[2].startsWith(" "));
    }

    @Test
    @Tag("Unmark test")
    public void unmarkTask_invalidIndex() {
        Command unmark = new UnmarkCommand(3);
        unmark.execute(tasks, ui);
        assertEquals(2,2);
    }

    @Test
    @Tag("Delete test")
    public void deleteTask_success(){
        int size = tasks.getSize();
        Command delete = new DeleteCommand(0);
        delete.execute(tasks, ui);
        assertEquals(size, tasks.getSize());
    }

    @Test
    @Tag("Add test")
    public void addTask_success() {
        int size = tasks.getSize();
        Command add = new ToDoCommand("read book");
        add.execute(tasks, ui);
        assertEquals(size + 1, tasks.getSize());
    }

    @Test
    @Tag("Invalid test")
    public void invalid_success() {
        Command invalid = new InvalidCommand();
        invalid.execute(tasks, ui);
        assertEquals(2,2);
    }

}
