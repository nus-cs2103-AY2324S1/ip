package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.ronaldo.RonaldoSaysDo;
import duke.util.TaskList;

public class TaskListTest {
    @Test
    public void markTask_withNegativeIndex() {
        try {
            RonaldoSaysDo ronaldoSaysDo = new RonaldoSaysDo();
            ronaldoSaysDo.handleTodoTask("todo borrow");
        } catch (DukeException e) {
            assertEquals("SUI, Enter mark command with positive index lesser than 2", e.getMessage());
        }
    }
    @Test
    public void getAllTasks_withEmptyList() {
        try {
            TaskList tasks = new TaskList();
            tasks.getAllToDo();
        } catch (DukeException e) {
            assertEquals("SUI, Oh no! No tasks for now! Add more tasks :)\n", e.getMessage());
        }
    }

}
