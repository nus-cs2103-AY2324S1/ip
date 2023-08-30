import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> tasksList = new ArrayList<>();

    public TaskList(String tasks) {
        Scanner scanner = new Scanner(tasks);
        while(scanner.hasNextLine()) {
            String formattedTask = scanner.nextLine();
            tasksList.add(Task.getTask(formattedTask));
        }
    }

    public int size() {
        return tasksList.size();
    }

    public String toString() {
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            int id = i + 1;
            task.append(id + "." + tasksList.get(i).toString() + "\n");
        }
        return("Here are the tasks in your list: \n" + task);
    }

    public void add(Task task) {
        tasksList.add(task);
    }



//    public void mark(int id) {
//        tasksList
//    }
}
