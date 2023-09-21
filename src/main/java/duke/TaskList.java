package duke;
import java.util.ArrayList;

// tasklist is the array list storing the tasks
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    //add task method allows a task to be added into the list
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    //add task from storage allows the tasks to be added into the task list when the bot is started

    public void addTaskFromStorage(Task task) {
        tasks.add(task);
    }

    //checks if the task list is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // allows tasks to be deleted from the list if user inputs require it to do so
    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task: \n" + "  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    // set the task at index
    public void set(int index, Task task) {
        tasks.set(index, task);
    }

    //shows the number of task in the task list currently
    public int size() {
        return tasks.size();
    }

    // get the task at specified index
    public Task get(int index) {
        return tasks.get(index);
    }
    // returns the tasks to an arraylist
    public ArrayList<Task> toArrayList() {
        return tasks;
    }

    // mark the task at a specified index as done
    public void markTaskAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(true);
        tasks.set(taskNumber, task);
        System.out.println("Nice! I've marked this task as done:\n" + "  " + tasks.get(taskNumber));
    }

    // unmarks the task at a specified index

    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(false);
        tasks.set(taskNumber, task);
        System.out.println("OK, I've marked this task as not done yet:\n" + "  " + tasks.get(taskNumber));
    }

    // prints out each task in the line
    public void listOfTasks() {
        System.out.println("Here are the tasks in your list: ");
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }
    public void findTasksContainingKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }
    public String toString() {
        return tasks.toString();
    }
}


