import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public String addTask(Task t) {
        taskList.add(t);
        return (String.format("added: %s.\nNow you have %d %s in the list.",
                t,
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }
    public String deleteTask(int index) throws DookException {
        if (index <= 0 || index > taskList.size()) {
            throw new DookException("That task does not exist on the list.");
        }

        Task curr = taskList.get(index - 1);
        taskList.remove(index - 1);
        String message = String.format("Ok, I have removed this task:\n   %s", curr);
        message += String.format("\nYou have %d %s in the list.",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks");
        return message;
    }
    public String markTask(int index, boolean value) throws DookException {
        if (index <= 0 || index > taskList.size()) {
            throw new DookException("That task does not exist on the list.");
        }
        Task curr = taskList.get(index - 1);
        if (value) {
            curr.markAsDone();
        } else {
            curr.unmarkAsDone();
        }
        String message = String.format("I have marked this task as %s:\n   %s",
                value ? "done" : "not done yet", curr);
        return message;
    }
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return (result.toString() + String.format("You have %d %s in the list.",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }
}
