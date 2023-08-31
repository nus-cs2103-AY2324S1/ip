import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("\t No tasks added yet.");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public int listSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        // Add task to the list
        tasks.add(task);
    }

    public Task getTask(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new DukeException("\t ☹ OOPS!!! Task not found.");
        }
    }


    public void deleteTask(int index) throws DukeException {
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\t ☹ OOPS!!! Please enter a valid task index to delete.");
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        // Mark task as done
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markDone();
            } else {
                throw new DukeException("☹ OOPS!!! Task not found.");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    public void markTaskAsUndone(int index) throws DukeException {
        // Mark task as undone
        try {
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markUndone();
            } else {
                throw new DukeException("☹ OOPS!!! Task not found.");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }
}
