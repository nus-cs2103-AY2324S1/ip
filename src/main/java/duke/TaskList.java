package duke;

import duke.task.AlreadyMarkedException;
import duke.task.AlreadyUnmarkedException;
import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;

    public TaskList(TaskList loadedList) {
        this.tasks = loadedList.tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String add(Task task) {
        tasks.add(task);
        return task.getAddMessage() + "\n" + task;
    }

    public String delete(Integer index) throws OutOfRangeException {

        if (index == null) {
            tasks.clear();
            return "Your task list has been cleared.";
        }

        int i = index;

        if (index < 0 || index >= tasks.size()) {
            throw new OutOfRangeException();
        }

        Task task = tasks.remove(i);
        StringBuilder builder = new StringBuilder();

        builder.append("As you wish. This task has been removed:\n");
        builder.append(task.toString());
        builder.append("\nYou now have ");
        builder.append(tasks.size());
        builder.append(" tasks in your list.");
        if (tasks.isEmpty()) {
            builder.append("\nCongratulations!");
        }

        return builder.toString();

    }

    public String mark(Integer index) throws OutOfRangeException, AlreadyMarkedException {

        if (index == null) {
            for (Task task : tasks) {
                try {
                    task.markDone();
                } catch (AlreadyMarkedException ignored) {
                }
            }
            return "All done.";
        }

        int i = index;

        if (i < 0 || i >= tasks.size()) {
            Task task = tasks.get(i);
            task.markDone();
            return "Mission accomplished.\n" + task.toString();
        } else {
            throw new OutOfRangeException();
        }

    }

    public String unmark(Integer index) throws OutOfRangeException, AlreadyUnmarkedException {

        if (index == null) {
            for (Task task : tasks) {
                try {
                    task.markUndone();
                } catch (AlreadyUnmarkedException ignored) {
                }
            }
            return "All undone.";
        }

        int i = index;

        if (i < 0 || i >= tasks.size()) {
            Task task = tasks.get(i);
            task.markUndone();
            return "Uncharacteristic of you. More work has been added to the pile.\n" + task.toString();
        } else {
            throw new OutOfRangeException();
        }

    }

    public String docRest() {
        if (tasks.isEmpty()) {
            return "...have a good rest.";
        } else {
            return "There's still lots of work that needs to be done. " +
                    "We can't afford to have you resting.";
        }
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {

        if (tasks.isEmpty()) {
            return "...\n" + "You don't have any tasks. Enjoy your day off.";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return output.toString();

    }

}
