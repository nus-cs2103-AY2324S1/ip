import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tasks {
    public Line line = new Line();
    private List<Task> tasks = new ArrayList<>();

    public void listTasks() {

        System.out.println(line);
        if (tasks.size() == 0) {
            System.out.println("    You do not have any tasks currently!");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                String s = String.format("    %s. %s", i + 1, task);
                System.out.println(s);
            }
        }
        System.out.println(line);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
