package bongo.command;

import java.time.LocalDateTime;

import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.DateHelper;
import bongo.helper.Ui;
import bongo.helper.Storage;
import bongo.task.Deadline;
import bongo.task.Event;
import bongo.task.Todo;
import bongo.task.Task;

public class AddCommand extends Command {
    String[] command;
    Task newTask;

    public AddCommand(String[] command) throws BongoException {
        this.command = command;
        this.initialiseTask();
    }

    private void initialiseTask() throws BongoException {
        String taskInput = "";
        if (command.length > 1) {
            taskInput = String.join(" ", command).substring(command[0].length() + 1);
        }
        switch (this.command[0]) {
        case "todo":
            if (command.length <= 1) {
                throw new BongoException("Please include the description of your todo.");
            }
            this.newTask = new Todo(taskInput);
            break;
        case "deadline":
            if (command.length <= 1) {
                throw new BongoException("Please include the description and datetime of your deadline.");
            }
            if (command.length <= 3) {
                throw new BongoException("Please include the datetime of your deadline.");
            }
            int index = taskInput.indexOf("/by");
            if (index == -1) {
                throw new BongoException("Please include the datetime of your deadline.");
            }
            String deadlineDesc = taskInput.substring(0, index - 1);
            String deadlineStr = taskInput.substring(index + 4);
            LocalDateTime deadline = DateHelper.formatDateTime(deadlineStr);
            this.newTask = new Deadline(deadlineDesc, deadline);
            break;
        case "event":
            if (command.length <= 1) {
                throw new BongoException("Please include the description and datetime of your event.");
            }
            if (command.length <= 4) {
                throw new BongoException("Please include the datetime of your event.");
            }
            int fromIndex = taskInput.indexOf("/from");
            int toIndex = taskInput.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new BongoException("Please include the to and from datetime of the event.");
            }
            String eventDesc = taskInput.substring(0, fromIndex - 1);
            String eventFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
            String eventTo = taskInput.substring(toIndex + 4);
            LocalDateTime from = DateHelper.formatDateTime(eventFrom);
            LocalDateTime to = DateHelper.formatDateTime(eventTo);
            this.newTask = new Event(eventDesc, from, to);
            break;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        ui.showAddedTask(newTask, tasks.getTotalTasks());
        storage.add(newTask);
    }
}
