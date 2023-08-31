package ekud.ui;

import java.util.List;

import ekud.command.Command;
import ekud.error.EkudException;
import ekud.state.Task;
import ekud.state.TaskList;
import ekud.util.Pair;

public abstract class Ui {
    public abstract Pair<Command, Boolean> readCommand();

    public abstract void showGreeting();

    public abstract void showFarewell();

    public abstract void showTaskList(TaskList taskList);

    public abstract void showTaskCount(TaskList taskList);

    public abstract void showTaskAdded(Task task);

    public abstract void showTaskRemoved(Task task);

    public abstract void showTaskMarked(Task task);

    public abstract void showTaskUnmarked(Task task);

    public abstract void showFoundTasks(List<Task> foundTasks);

    public abstract void showError(EkudException error);
}
