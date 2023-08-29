import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void mark(String[] splitOutput) {
        System.out.println("Nice! I've marked this task as done:");
        Task selectedTask = tasks.get(Integer.parseInt(splitOutput[1]) - 1);
        selectedTask.mark();
        System.out.println(selectedTask.toString());
    }

    public void unmark(String[] splitOutput) {
        System.out.println(" OK, I've marked this task as not done yet:");
        Task selectedTask = tasks.get(Integer.parseInt(splitOutput[1]) - 1);
        selectedTask.unmark();
        System.out.println(selectedTask.toString());
    }

    public void delete(String[] splitOutput) {
        try {
            Integer deleteIndex = Integer.valueOf(splitOutput[1]);
            Task deletedTask = tasks.get(deleteIndex - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            tasks.remove(deleteIndex - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void list() {
        if (tasks.isEmpty()) {
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }
}
