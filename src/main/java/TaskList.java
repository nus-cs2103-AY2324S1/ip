import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private TaskList update(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }

    TaskList add(Task task) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
        return update(currentTasks);
    }

    TaskList delete(int index) throws IOException {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.remove(index - 1);
        return update(currentTasks);
    }

    TaskList mark(int index) throws IOException {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.mark());
        return update(currentTasks);
    }

    TaskList unmark(int index) throws IOException {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.unmark());
        return update(currentTasks);
    }

    String dateSearch(LocalDate date) {
        String res= "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).happenOnThatDate(date)) {
                res = res + Integer.toString(i + 1) +
                        ": " + tasks.get(i).toString() + "\n";
            }
        }
        return res;
    }


    String saveMessage() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
