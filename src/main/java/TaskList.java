import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<String> tasksList = new ArrayList<>();

    public TaskList(String tasks) {
        Scanner scanner = new Scanner(tasks);
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();
            tasksList.add(task);
        }
    }

    public int size() {
        return tasksList.size();
    }
}
