package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Task;

import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    String commandType;
    boolean isExit;
    LocalDate by;

    DeadlineCommand(String name, LocalDate by) {
        this.commandType = "Deadline";
        this.isExit = false;
        this.name = name;
        this.by = by;
    }

    public DeadlineCommand(String name, String by) {
        this.commandType = "Deadline";
        this.isExit = false;
        this.name = name;
        this.by = LocalDate.parse(by);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new deadline task
        Task newDeadlineTask = new Deadline(name, by);
        // 2. Add to newTodoTask to tasks
        tasks.addTask(newDeadlineTask);
        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newDeadlineTask, tasks.getNumTotalTasks());
    }
}
