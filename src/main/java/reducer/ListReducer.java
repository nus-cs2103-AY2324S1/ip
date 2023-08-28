package reducer;

import java.io.PrintStream;
import java.util.List;

import action.Action;
import state.State;
import state.Task;
import util.Pair;

public final class ListReducer {
    public Pair<State, Boolean> run(State state, Action action, PrintStream out) {
        if (!state.hasTasks()) {
            out.println("No tasks yet. Add one!");
            return new Pair<>(state, true);
        }

        out.println("Here are the tasks in your list:");
        List<Task> tasks = state.getTasks();
        for (int taskId = 1; taskId <= tasks.size(); taskId++) {
            // Add padding to align single-digit numbers if we'll render two-digit numbers
            // later on.
            if (tasks.size() > 9 && taskId < 10) {
                out.print(" ");
            }
            out.print(taskId);
            Task task = state.getTask(taskId);
            out.println(". " + task.toString());
        }

        return new Pair<>(state, true);
    }
}
