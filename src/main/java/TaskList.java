import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import exceptions.EmptyTaskDescriptionException;
import exceptions.InvalidDateFormatException;
import exceptions.InvalidTaskNumberException;
import exceptions.TaskAlreadyDoneException;
import exceptions.TaskNotDoneException;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
    * Exits the chatbot.
    */
    public void bye() {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    /**
    * Prints all tasks currently in the list.
    */
    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
    * Deletes the task at the specified index provided by the command.
    * @param command The user input containing the task index to delete.
    */
    public void deleteTask(String command) throws InvalidTaskNumberException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Task number out of range.");
            }
            
            Task removedTask = tasks.remove(taskNo - 1);
            ui.printMessage("Noted. I've removed this task:\n       " + removedTask + "\n     Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    /**
    * Marks the task at the specified index as done.
    * @param command The user input containing the task index to mark.
    */
    public void markTask(String command) throws InvalidTaskNumberException, TaskAlreadyDoneException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }
            
            Task task = tasks.get(taskNo - 1);
            if (task.isDone()) {
                throw new TaskAlreadyDoneException("Task \"" + task.getDescription() + "\" is already done.");
            }

            task.markAsDone();
            ui.printMessage("Nice! I've marked this task as done:\n       " + task);
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
    * Unmarks the task at the specified index, marking it as not done.
    * @param command The user input containing the task index to unmark.
    */
    public void unmarkTask(String command) throws InvalidTaskNumberException, TaskNotDoneException{
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }

            Task task = tasks.get(taskNo - 1);
            if (!task.isDone()) {
                throw new TaskNotDoneException("Task \"" + task.getDescription() + "\" is not done yet.");
            }

            task.unmarkAsDone();
            ui.printMessage("OK, I've marked this task as not done yet:\n       " + task);
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
    * Adds a new ToDo task to the task list.
    * @param command The user input containing the task description.
    */
    public void addTodo(String command) throws EmptyTaskDescriptionException {
        if (command.length() <= 4) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        
        String description = command.substring(5);
        if (description.isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        tasks.add(new Todo(description));
        ui.printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
    * Adds a new Deadline task to the task list.
     * @param command The user input containing the task description and its deadline.
     */
    public void addDeadline(String command) throws EmptyTaskDescriptionException {
        if (command.length() <= 8) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline or its date cannot be empty.");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        ui.printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
    * Adds a new Event task to the task list.
    * @param command The user input containing the event details.
    */
    public void addEvent(String command) throws EmptyTaskDescriptionException {
        if (command.length() <= 5) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event or its time cannot be empty.");
        }

        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        ui.printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
    * Prints the tasks that are scheduled on a specific date.
    *
    * @param dateStr The specific date in which to filter tasks.
    */
    public void eventsOndate(String dateStr) throws InvalidDateFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;
    
        try {
            date = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("     Invalid date format. Please use the format 'd/M/yyyy'.");
        }
    
        System.out.println("    ____________________________________________________________");
        System.out.println("     Tasks on " + date.format(DateTimeFormatter.ofPattern("M d yyyy")) + ":");
        int count = 0;
        boolean hasTasks = false;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().toLocalDate().equals(date)) {
                    System.out.println("     " + (count + 1) + ". " + task);
                    hasTasks = true;
                }
            }
            count++;
        }

        if (!hasTasks) {
            System.out.println("     No tasks on this date.");
        }

        System.out.println("    ____________________________________________________________");
    }
}
