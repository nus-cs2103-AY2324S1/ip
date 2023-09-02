import task.Task;
import task.TaskList;

public class Ui {
    public void printTask(Task task) {
        System.out.println(task);
    }

    public void list(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1);
            System.out.println("." + list.get(i));
        }
    }
}
