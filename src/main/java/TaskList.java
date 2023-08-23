import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int i) {
        return taskList.get(i - 1);
    }

    public void doTask(String input) {
        int number = Integer.parseInt(input.substring(5));
        getTask(number).markDone();
        System.out.println("ALRIGHT NICE I'll mark this as completed :)");
        System.out.println(getTask(number));
    }

    public void undoTask(String input) {
        int number = Integer.parseInt(input.substring(7));
        getTask(number).markUndone();
        System.out.println("Oh nooo I will mark this undone then :(");
        System.out.println(getTask(number));
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added:\n" + task.toString());
    }

    public void printList() {
        for (Task t : taskList) {
            System.out.printf("%d.%s%n",taskList.indexOf(t) + 1, t.toString());
        }
    }
}
