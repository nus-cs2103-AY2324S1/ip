import java.util.ArrayList;

public class FunnyList {

    private ArrayList<Task> taskList;

    public FunnyList() {
        this.taskList = new ArrayList<>();
    }

    public void displayList() {
        Duke.printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + String.valueOf(i + 1) + ". " + taskList.get(i));
        }
        Duke.printLine();
    }

    public Task get(int i) throws DukeException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        return taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
        Duke.printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + String.valueOf(this.taskList.size()) + " tasks in the list");
        Duke.printLine();
    }
}
