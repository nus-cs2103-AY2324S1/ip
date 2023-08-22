import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int i) {
        return taskList.get(i - 1);
    }

    public void doTask(int i) {
        getTask(i).markDone();
        System.out.println("ALRIGHT NICE I'll mark this as completed :)");
        System.out.println(getTask(i));
    }

    public void undoTask(int i) {
        getTask(i).markUndone();
        System.out.println("Oh nooo I will mark this undone then :(");
        System.out.println(getTask(i));
    }

    public void addTask(String item) {
        this.taskList.add(Task.newTask(taskList.size() + 1, item));
        System.out.println("Added: " + item);
    }

    public void printList() {
        for (Task t : taskList) {
            System.out.println(t.toString());
        }
    }
}
