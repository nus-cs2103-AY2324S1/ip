package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Events;
import Duke.Tasks.ToDos;
import Duke.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command{
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;
    public EventCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        String task = "event";


        try {
            Events newTask = new Events(getDescription(task, input), getStartDate(input), getEndDate(task, input));
            taskList.addTask(newTask);
            ui.printAddTask(taskList, newTask);
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printInvalidTimeError();
        }
    }
}
