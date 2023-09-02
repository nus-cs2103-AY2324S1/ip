import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void saveList() {
        Storage.saveTask(tasks);
    }

    public void updateFromStorage() {
        try {
            tasks = Storage.loadTasks();
        } catch (DukeException e) {
            Ui.print("No tasks found in storage, starting new list");
        }
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.print("Got it. I've added this task: \n" + task + "\nnow you have "
                + tasks.size() + " tasks in the list");
    }

    public void deleteTask(int i) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("There are no tasks to delete");
        } else if (tasks.size() < i) {
            throw new DukeException("Task index out of range");
        }
        Task deleted = tasks.remove(i);
        Ui.print("Noted. I've removed this task:\n" + deleted + "\nNow you have "
                + tasks.size() + " tasks in the list");
    }

    public void markTask(int i) throws DukeException {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) throws DukeException {
        tasks.get(i).unmark();
    }
}
