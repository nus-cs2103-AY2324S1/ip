package duke.commands;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void run_emptyTasksList_success() throws CommandException {
        TaskList tasks = new TaskList();
        Command command = new ListCommand();

        CommandResult result = command.run(tasks);

        assertEquals(List.of("You have no tasks in your list!"), result.response);
    }

    @Test
    public void run_nonEmptyTaskList_commandExceptionThrown() throws CommandException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("assignment", LocalDateTime.parse("2023-09-10T12:00"));
        Event event = new Event("meeting", LocalDateTime.parse("2023-09-10T12:00"), LocalDateTime.parse("2023-09-10T16:00"));
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);
        Command command = new ListCommand();

        CommandResult result = command.run(tasks);

        assertEquals(List.of("Here are the tasks in your list:", "1. " + todo, "2. " + deadline, "3. " + event, "You have 3 tasks in your list."), result.response);
    }

}
