package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Deadlines;
import Duke.Tasks.ToDos;
import Duke.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Constructor for a deadline command
     *
     * @param input The input for the command
     * @param taskList The taskList to store the task
     * @param storage The storage system to store the list
     * @param ui The ui to print the commands
     */
    public DeadlineCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Execute the command itself
     *
     * @throws DukeException When there is an error saving
     */
    @Override
    public void execute() throws DukeException {
        String task = "deadline";

        try {
            Deadlines newTask = new Deadlines(getDescription(task, input), getEndDate(task, input));
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
