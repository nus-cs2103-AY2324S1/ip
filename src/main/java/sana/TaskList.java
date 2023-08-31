package sana;
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
        return("Here are the tasks in your list:\n" + task);
    }

    public void add(Task task) {
        tasksList.add(task);
    }

    public Task delete(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        Task temp = tasksList.get(taskId - 1);
        tasksList.remove(taskId - 1);
        return temp;
    }

    public void update(Storage storage) {
        storage.clear("/Users/ariellacallista/Desktop/SanaTasks.txt");
        for (Task task : tasksList) {
            storage.save("/Users/ariellacallista/Desktop",
                    "/Users/ariellacallista/Desktop/SanaTasks.txt", task);
        }
    }

    public void mark(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + tasksList.get(taskId - 1).toString());

    }

    public void unmark(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + tasksList.get(taskId - 1).toString());
    }

}
