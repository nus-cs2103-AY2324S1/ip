package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;

import java.time.LocalDate;

public class EventCommand extends AddCommand {
    String commandType;
    boolean isExit;
    LocalDate from, to;

    EventCommand(String name, LocalDate from, LocalDate to) {
        this.commandType = "Event";
        this.isExit = false;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public EventCommand(String name, String from, String to) {
        this.commandType = "Event";
        this.isExit = false;
        this.name = name;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new deadline task
        Task newEventTask = new Event(name, from, to);

        // 2. Add to newTodoTask to tasks
        tasks.addTask(newEventTask);

        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newEventTask, tasks.getNumTotalTasks());
    }
}
