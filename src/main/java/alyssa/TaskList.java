import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.AlyssaArgumentException;

/**
 * This class supports Alyssa by encapsulating the current tasks.
 */
public class TaskList {
    private static final String line = "____________________________________________________________";
    private List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    protected void listTasks() {
        int counter = 1;
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(counter + "." + task.toString());
            counter++;
        }
        System.out.println(line);
    }
    protected void markTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    protected void unmarkTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    protected void addTodo(String desc) throws AlyssaArgumentException {
        if (desc.isEmpty()) {
            throw new AlyssaArgumentException("Please specify a description for the todo.");
        }
        Task newTodo = new Todo(desc);
        taskList.add(newTodo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    protected void addDeadline(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /by ");
        if (parsed.length < 2) {
            throw new AlyssaArgumentException("Incorrect deadline syntax. Syntax: deadline desc /by date");
        }
        String desc = parsed[0];
        String by = parsed[1];
        Task newDeadline;
        try {
            newDeadline = new Deadline(desc, LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            throw new AlyssaArgumentException("Invalid by. Syntax: yyyy-mm-dd");
        }
        taskList.add(newDeadline);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    protected void addEvent(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /from | /to ");
        if (parsed.length < 3) {
            throw new AlyssaArgumentException("Incorrect event syntax. Syntax: event desc /from date /to date");
        }
        String desc = parsed[0];
        String from = parsed[1];
        String to = parsed[2];
        Task newEvent = new Event(desc, from, to);
        taskList.add(newEvent);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    protected void deleteTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task toDelete = taskList.get(index - 1);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete.toString());
        taskList.remove(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    public List<Task> getTaskList() {
        return this.taskList;
    }
}
