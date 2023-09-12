package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.ScheduleCommand;
import exceptions.DukeException;
import io.Storage;
import ui.Ui;
public class ScheduleCommandTest {
    /**
     * Test for execute method for ScheduleCommand.
     * @throws DukeException
     */
    @Test
    public void executeTest() throws DukeException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        try {
            taskList.add(new DeadlineTask("return book", "2/12/2019 1800"));
            taskList.add(new EventTask("project meeting",
                    "1/12/2019 1400", "1/12/2019 1600"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ScheduleCommand scheduleCommand = new ScheduleCommand("schedule 1/12/2019");
        scheduleCommand.execute(taskList, ui, storage);
        assertEquals("2. [E][ ] project meeting (from: Dec 01 2019 1400 to: Dec 01 2019 1600)\n",
                scheduleCommand.getOutput());
    }
}
