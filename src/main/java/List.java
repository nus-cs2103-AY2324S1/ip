import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString());
        System.out.println(this);
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

    @Override
    public String toString() {
        return "Now you have " + taskList.size() + " task(s) in the list";
    }
}
