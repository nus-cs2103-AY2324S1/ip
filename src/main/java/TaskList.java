import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void listAllTask() {

        System.out.println(Ui.showLine());
        System.out.println("\tHere " + (tasks.size() > 1 ? "are" : "is") +
                " the " + (tasks.size() > 1 ? "tasks" : "task") + " in your list: ");
        if (tasks.size() > 0) {
            for (int i = 1; i < tasks.size() + 1; i++) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        }
        System.out.println();
        System.out.println(Ui.showLine());
    }

    public void copyArrInto(ArrayList<String> here) {

        for (Task t : this.tasks) {
            here.add(t.contentLine());
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
