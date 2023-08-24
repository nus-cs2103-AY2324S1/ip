import java.util.ArrayList;

public class toDoList {
    protected ArrayList<Task> taskList;

    public toDoList() {
        taskList = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Osps! Your to-do list is empty. Add some tasks now:)";
        }

        String result = "YOUR TO-DO LIST:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            int order = i + 1;
            result+= order + ". " + taskList.get(i) +"\n";
        }
        result+="----------END OF YOUR TO-DO LIST----------";
        return result;
    }

    public void addTask(Task task) {
        taskList.add(task);
        String taskName = task.name;
        System.out.println("added: " + taskName);
    }

    public void markTask(int i) {
        int order = i - 1;
        taskList.get(order).markAsDone();
    }

    public void unmarkTask(int i) {
        int order = i - 1;
        taskList.get(order).markAsNotDone();
    }
}
