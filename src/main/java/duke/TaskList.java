package duke;
import java.util.ArrayList;

// tasklist is the array list storing the tasks
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    //add task method allows a task to be added into the list
    public String addTask(Task task) {
        String result = "";
        tasks.add(task);
        result = result + "Got it. I've added this task:\n";
        result = result + " " + task + "\n";
        result = result + "Now you have " + tasks.size() + " tasks in the list.";
        return result;
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
    public String deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        String result = "";
        result = result +" "+ "Noted. I've removed this task: \n" + "  " + task + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return result;
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
    public String markTaskAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(true);
        tasks.set(taskNumber, task);
        return "Nice! I've marked this task as done:\n" + "  " + tasks.get(taskNumber);
    }

    // unmarks the task at a specified index

    public String unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(false);
        tasks.set(taskNumber, task);
        return "OK, I've marked this task as not done yet:\n" + "  " + tasks.get(taskNumber);
    }

    // prints out each task in the line
    public String listOfTasks() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list: \n");
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                result.append(i).append(".").append(tasks.get(i - 1)).append("\n");
            }
        }
        return result.toString();
    }
    public String findTasksContainingKeyword(String keyword) {
        StringBuilder result = new StringBuilder();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        result.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return result.toString();
    }
    public String toString() {
        return tasks.toString();
    }
}


