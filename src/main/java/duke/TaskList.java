package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    // The list of tasks
    private List<Task> tasks;
    // The formatter for the date and time
    private final DateTimeFormatter inputFormatter;

    /**
     * A constructor for the task list if an initial list exist
     *
     * @param tasks the list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * A constructor for the task list if an initial list does not exist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a success message when a task has been successfully added.
     *
     * @return the success message
     */
    public String printAddTaskSuccessMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n");
        message.append(" ");
        message.append(this.tasks.get(this.tasks.size() - 1).toString());
        message.append("\n");
        message.append("Now you have ");
        message.append(this.tasks.size());
        message.append(" task(s) in the list.");
        return message.toString();
    }

    /**
     * Returns a success message when a task has been successfully deleted.
     *
     * @return the success message
     */
    public String printRemoveTaskSuccessMessage(Task task) {
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task:\n");
        message.append(" ");
        message.append(task.toString());
        message.append("\n");
        message.append("Now you have ");
        message.append(this.tasks.size());
        message.append(" task(s) in the list.");
        return message.toString();
    }

    /**
     * Takes in the task details and add the task to the list.
     *
     * @param task the task description
     * @param typeOfTask the type of the task to be added
     * @throws DukeException if invalid or incorrect command
     */
    public void addTask(String[] task, String typeOfTask) throws DukeException {
        switch (typeOfTask) {
        case "event":
            try {
                this.tasks.add(new Event(task[0], LocalDateTime.parse(task[1], this.inputFormatter),
                        LocalDateTime.parse(task[2], this.inputFormatter)));
            } catch (DateTimeParseException exception) {
                System.out.println("Invalid start and end date/time. The format should be yyyy-mm-dd hh:mm");
                break;
            }
            System.out.println(printAddTaskSuccessMessage());
            break;
        case "todo":
            this.tasks.add(new Todo(task[1]));
            System.out.println(printAddTaskSuccessMessage());
            break;
        case "deadline":
            try {
                this.tasks.add(new Deadline(task[0], LocalDateTime.parse(task[1], this.inputFormatter)));
            } catch (DateTimeParseException exception) {
                System.out.println("Invalid start and end date/time. The format should be yyyy-mm-dd hh:mm");
                break;
            }
            System.out.println(printAddTaskSuccessMessage());
            break;
        default:
            throw new DukeException(ExceptionTypes.INVALIDCOMMAND);
        }
    }

    /**
     * Takes in the command to delete a task
     * and remove the task from the list.
     *
     * @param taskNumber the task number of the task
     * @throws DukeException if invalid or incorrect command
     */
    public void removeTask(int taskNumber) throws DukeException {
        if (this.tasks.isEmpty()) {
            throw new DukeException(ExceptionTypes.DELETEEMPTYLIST);
        }
        if (taskNumber > this.tasks.size() || taskNumber <= 0) {
            throw new DukeException(ExceptionTypes.INVALIDTASKNUMBER);
        }
        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(task);
        System.out.println(printRemoveTaskSuccessMessage(task));
    }

    /**
     * Takes in the task command to mark or unmark
     * a task and mark the task as done or mark a task as
     * not done
     *
     * @param taskNumber the task number of the task
     * @param action to mark or unmark the task
     * @throws DukeException if invalid or incorrect command
     */
    public void markTask(int taskNumber, String action) throws DukeException {
        switch(action) {
        case "mark":
            if (this.tasks.isEmpty()) {
                throw new DukeException(ExceptionTypes.MARKEMPTYLIST);
            }
            if (taskNumber > this.tasks.size() || taskNumber <= 0) {
                throw new DukeException(ExceptionTypes.INVALIDTASKNUMBER);
            }
            System.out.println("Nice! I've marked this task as done:");
            Task markTask = this.tasks.get(taskNumber - 1);
            markTask.markAsDone();
            System.out.println(markTask.toString());
            break;
        case "unmark":
            if (this.tasks.isEmpty()) {
                throw new DukeException(ExceptionTypes.UNMARKEMPTYLIST);
            }
            if (taskNumber > this.tasks.size() || taskNumber <= 0) {
                throw new DukeException(ExceptionTypes.INVALIDTASKNUMBER);
            }
            System.out.println("OK, I've marked this task as not done yet:");
            Task unmarkTask = this.tasks.get(taskNumber - 1);
            unmarkTask.markAsNotDone();
            System.out.println(unmarkTask.toString());
            break;
        default:
            throw new DukeException(ExceptionTypes.INVALIDCOMMAND);
        }
    }

    /**
     * Takes in the keyword and find the tasks that
     * contains the keyword and print the tasks
     *
     * @param keyword the keyword to be searched
     */
    public void findTask(String keyword) {
        if (this.tasks.isEmpty()) {
            System.out.println("There are no matching tasks in the list.");
            return;
        }
        int count = 1;
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword) || task.getTaskType().contains(keyword)) {
                if (count == 1) {
                    System.out.println("Here are the matching task(s) in your list:");
                }
                System.out.println(count + ". " + task.toString());
                count++;
            }
        }
        if (count == 1) {
            System.out.println("There are no matching tasks in the list.");
        }
    }
}
