package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = null;
    }
    public ArrayList<Task> task() {
        return tasks;
    }

    public int num() {
        return tasks.size();
    }


    /**
     * Return the list of task descriptions
     *
     * @return an ArrayList with the String of description of each task in the file
     */
    public ArrayList<String> listTasks() {
        ArrayList<String> msg = new ArrayList<String>();
        if (tasks.isEmpty()) {
            msg.add("List is empty");
        }

        System.out.println("List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            msg.add((i + 1) + ". " + task.toString()) ;
        }
        return msg;
    }

    /**
     * Mark the task as done
     *
     * @param input string that user key in to dictate which task to mark
     * @param number number of tasks in the list
     */
    public void mark(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                item.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(item.toString());
            } else {
                System.out.println("You have chosen an invalid task");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
        }
    }

    /**
     * Mark task as undone
     *
     * @param input string that user key in to dictate which task to unmark
     * @param number number of tasks in the list
     */
    public void unmark(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                item.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(item.toString());
            } else {
                System.out.println("You have chosen an invalid task");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
        }
    }

    /**
     * Remove the task from the list
     *
     * @param input string that user key in to dictate which task to delete
     * @param number number of task in the list
     * @return the number of task in the list after deleting
     */
    public int delete(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                number--;
                System.out.println("Noted. I've removed this task:");
                System.out.println(item.toString());
                System.out.println("Now you have " + number + " tasks in the list");
                return number;
            } else {
                System.out.println("You have chosen an invalid task");
                return number;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
            return number;
        }
    }

    /**
     * Add the given task to the ArrayList
     * @param task task to be added into the ArrayList
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public ArrayList<String> find(String input) {
        String trimmedInput = input.substring(5).trim();
        ArrayList<String> matchingTasks = new ArrayList<>();
        int number = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(trimmedInput)) {
                String string = task.toString();
                matchingTasks.add(number + "." + string);
                number++;
            }
        }
        if (!matchingTasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            return matchingTasks;
        } else {
            matchingTasks.add("There are no matching tasks in your list");
            return matchingTasks;
        }

    }
}
