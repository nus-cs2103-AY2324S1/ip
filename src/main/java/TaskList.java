import java.util.ArrayList;

public class TaskList {
    private static final String HORLINE = "_____________________________________________________\n";
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    String del(Storage storage, int taskIndex) {
        String temp = tasks.get(taskIndex).toString();
        tasks.remove(taskIndex);
        storage.delete(taskIndex);
        return HORLINE + "Noted. I've removed this task:\n  " + temp + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    String addDeadline(Storage storage, String inp, String by) {
        Deadline newTask = new Deadline(inp, false, by);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    String addEvent(Storage storage, String inp, String from, String to) {
        Event newTask = new Event(inp, false, from, to);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    String addTodo(Storage storage, String inp) {
        Todo newTask = new Todo(inp, false);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    void toggle(Storage storage, String requestType, int taskIndex) {
        if (taskIndex > tasks.size()) {
            System.out.println("Invalid item selected. Please try again.");
        } else {
            Task task = tasks.get(taskIndex);
            if (requestType.equals("mark") && task.getStatusIcon().equals("[X] ")) {
                System.out.println(HORLINE + "Task is currently marked. Did you mean to unmark the task?\n" + HORLINE);
            } else if (requestType.equals("unmark") && task.getStatusIcon().equals("[ ] ")) {
                System.out.println(HORLINE + "Task is currently unmarked. Did you mean to mark the task?\n" + HORLINE);
            } else {
                task.toggle();
                String updatedTaskString = "";
                try {
                    updatedTaskString = ((Deadline) task).toFileString();
                } catch (ClassCastException e) {
                    try {
                        updatedTaskString = ((Todo) task).toFileString();
                    } catch (ClassCastException f) {
                        updatedTaskString = ((Event) task).toFileString();
                    }
                }
                storage.updateTask(taskIndex, updatedTaskString);
                if (task.getStatusIcon().equals("[X] ")) {
                    System.out.println(HORLINE + "Nice! I've marked this task as done:");
                    System.out.println("  [X] " + task.getDescription() + "\n" + HORLINE);
                } else {
                    System.out.println(HORLINE + "OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + task.getDescription() + "\n" + HORLINE);
                }
            }
        }
    }

    String lst() {
        String temp = "Here are the tasks in your list:\n";
        for(int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            temp += String.valueOf(i + 1) + "." + tempTask.toString() + "\n";
        }  
        return HORLINE + temp + HORLINE;
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    int size() {
        return this.tasks.size();
    }
}
