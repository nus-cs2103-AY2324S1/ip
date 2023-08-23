import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
        System.out.println("\tadded: " + task.description);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void list() {
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
        }
        System.out.println(returnString);
    }
}
