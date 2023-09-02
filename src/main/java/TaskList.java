import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("There are currently no tasks in your list"
                    + Ui.SEPARATOR);
        } else {
            int i = 1;
            System.out.println("Here are the tasks in your list: ");
            for (Task t : this.taskList) {
                System.out.println(i + ". " + t.toString());
                i++;
            }
            System.out.println(Ui.SEPARATOR);
        }
    }
    public void markTask(int num) {
        Task t = taskList.get(num - 1);
        t.markDone();
        System.out.println("Nice! I've marked this task as done: \n"
                + t
                + Ui.SEPARATOR);
    }

    public void unmarkTask(int num) {
        Task t = taskList.get(num - 1);
        t.markUndone();
        System.out.println("Nice! I've un-marked this task: \n"
                + t
                + Ui.SEPARATOR);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Got it. Task successfully added: \n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list");
    }

    public void deleteTask(int num) {
        Task deletedTask = taskList.remove(num - 1);
        System.out.println("Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list");
    }

    public int taskSize() {
        return this.taskList.size();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
