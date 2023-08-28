package reducer;

import java.io.PrintStream;

import action.Action;
import action.ByeAction;
import action.CreateDeadlineAction;
import action.CreateEventAction;
import action.CreateTodoAction;
import action.DeleteAction;
import action.ListAction;
import action.MarkAction;
import action.UnmarkAction;
import error.ArgumentException;
import state.DeadlineTask;
import state.EventTask;
import state.State;
import state.Task;
import state.TodoTask;
import util.Pair;

public final class Reducer {
    private final ListReducer list = new ListReducer();

    public Pair<State, Boolean> run(State state, Action action, PrintStream out) {
        if (action instanceof ByeAction) {
            return new Pair<>(state, false);
        } else if (action instanceof ListAction) {
            return list.run(state, action, out);
        } else if (action instanceof CreateTodoAction) {
            CreateTodoAction createTodoAction = (CreateTodoAction) action;
            Task newTask = new TodoTask(createTodoAction.getTitle(), false);
            State newState = state.addTask(newTask).getFirst();
            out.println("Got it. I've added this task:");
            out.println("   " + newTask);
            out.println("Now you have " + newState.getTasks().size() + " tasks in the list.");
            return new Pair<>(newState, true);
        } else if (action instanceof CreateDeadlineAction) {
            CreateDeadlineAction createDeadlineAction = (CreateDeadlineAction) action;
            Task newTask = new DeadlineTask(createDeadlineAction.getTitle(), false, createDeadlineAction.getBy());
            State newState = state.addTask(newTask).getFirst();
            out.println("Got it. I've added this task:");
            out.println("   " + newTask);
            out.println("Now you have " + newState.getTasks().size() + " tasks in the list.");
            return new Pair<>(newState, true);
        } else if (action instanceof CreateEventAction) {
            CreateEventAction createEventAction = (CreateEventAction) action;
            Task newTask = new EventTask(createEventAction.getTitle(), false, createEventAction.getFrom(),
                    createEventAction.getTo());
            State newState = state.addTask(newTask).getFirst();
            out.println("Got it. I've added this task:");
            out.println("   " + newTask);
            out.println("Now you have " + newState.getTasks().size() + " tasks in the list.");
            return new Pair<>(newState, true);
        } else if (action instanceof MarkAction) {
            MarkAction markAction = (MarkAction) action;
            Pair<State, Task> result = state.markTask(markAction.getTaskId());
            State newState = result.getFirst();
            Task markedTask = result.getSecond();
            if (markedTask == null) {
                throw new ArgumentException("Invalid task identifier");
            }
            out.println("Nice! I've marked this task as done:");
            out.println("   " + markedTask);
            return new Pair<>(newState, true);
        } else if (action instanceof UnmarkAction) {
            UnmarkAction unmarkAction = (UnmarkAction) action;
            Pair<State, Task> result = state.unmarkTask(unmarkAction.getTaskId());
            State newState = result.getFirst();
            Task unmarkedTask = result.getSecond();
            if (unmarkedTask == null) {
                throw new ArgumentException("Invalid task identifier");
            }
            out.println("OK, I've marked this task as not done yet:");
            out.println("   " + unmarkedTask);
            return new Pair<>(newState, true);
        } else if (action instanceof DeleteAction) {
            DeleteAction deleteAction = (DeleteAction) action;
            Pair<State, Task> result = state.deleteTask(deleteAction.getTaskId());
            State newState = result.getFirst();
            Task deletedTask = result.getSecond();
            if (deletedTask == null) {
                throw new ArgumentException("Invalid task identifier");
            }
            out.println("Noted. I've removed this task:");
            out.println("   " + deletedTask);
            out.println("Now you have " + newState.getTasks().size() + " tasks in the list.");
            return new Pair<>(newState, true);
        } else {
            throw new UnsupportedOperationException("Invalid action!");
        }
    }
}
