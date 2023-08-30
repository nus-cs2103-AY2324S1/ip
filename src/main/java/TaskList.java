import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(String inData, boolean markAsDone) throws SimonException {
        String[] split = inData.split(" ");

        int index;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }

        if (this.tasks.isEmpty()) {
            throw new SimonException("There are no tasks to mark.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }

        if (markAsDone) {
            tasks.get(index).markAsDone();
            saveTasksToFile();
            System.out.println("Nice! I've marked this task as done:\n[X] " + tasks.get(index) + Simon.NSPACE);
        } else {
            tasks.get(index).markAsUndone();
            saveTasksToFile();
            System.out.println("OK, I've marked this task as not done yet:\n[ ] " + tasks.get(index) + Simon.NSPACE);
        }
    }

    private void deleteTask(String inData) throws SimonException {
        String[] split = inData.split(" ");

        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }

        if (this.tasks.isEmpty()) {
            throw new SimonException("There are no tasks to delete.");
        }
        if (index < 0 || index >= this.tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        saveTasksToFile();
        System.out.println("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() - 1 > 1 ? "tasks" : "task") + Simon.NSPACE);
    }

}
