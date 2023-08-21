import java.util.ArrayList;

public class TaskList {

    private static final String DIVIDER = "____________________________________________________________";
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        this.list.add(newTask);
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void markTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        this.list.get(index - 1).markDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    public void unmarkTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        this.list.get(index - 1).unmarkDone();
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    public void deleteTask(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
        this.list.remove(index - 1);
    }

    public void listTasks() {
        System.out.println(DIVIDER);
        if (this.list.isEmpty()) {
            System.out.println("You have no tasks so far.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println((i + 1) + ". " + this.list.get(i));
            }
        }

        System.out.println(DIVIDER);
    }
}
