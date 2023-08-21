import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList add(Task task) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
        return new TaskList(currentTasks);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
